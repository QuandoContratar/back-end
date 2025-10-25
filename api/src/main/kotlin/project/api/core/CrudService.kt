package project.api.core

import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Example
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import project.api.app.candidates.data.Candidate
import project.api.core.enums.EntitiesEnum
import project.api.core.errors.DuplicateDataException
import project.api.core.errors.NotFoundByIdException
import project.api.core.errors.UnprocessableEntityException
import project.api.core.utils.ArrayUtils
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

@Service
abstract class CrudService<T : Any>(
    val repository: JpaRepository<T, Int>
) {
    fun findAll( // TODO: receber um criteria builder e fazer oq ter q fazer
        example:T?,
        ignoreCase:Boolean
    ):List<T> {
        if (example == null){
            val list:List<T> = repository.findAll();

            return list;
        }
        val filter:Example<T> = filterCombiner(example, ignoreCase);

        val list:List<T> = repository.findAll(filter);

        return list;
    }

//    fun <R> findAll(
//        example: T? = null,
//        ignoreCase: Boolean = false,
//        transform: ((T) -> R)? = null
//    ): List<R> {
//        val list = if (example == null) {
//            repository.findAll()
//        } else {
//            val filter:Example<T> = filterCombiner(example, ignoreCase)
//            repository.findAll(filter)
//        }
//
//        return if (transform != null) list.map(transform) else list as List<R>
//    }

    private fun filterCombiner(example:T, ignoreCase:Boolean):Example<T>{
        val properties = example::class.members.filterIsInstance<KProperty1<T, *>>();
        val combiner:ExampleMatcher = ExampleMatcher.matching();

        if (ignoreCase) combiner.withIgnoreCase();

        for (property in properties){
            if (property.get(example) != null) {
                combiner.withMatcher(property.name, ExampleMatcher.GenericPropertyMatchers.contains());
            }
        }
        val filter:Example<T> = Example.of(example, combiner);

        return filter;
    }
    fun findById(id:Int):T {
        val entity = repository.findById(id);

        if (entity.isPresent) return entity.get();
        throw NotFoundByIdException(id);
    }
    fun insert(dto:T, example:T?):T {
        if (example != null) {
            val isDuplicated: Boolean = repository.exists(filterCombiner(example, false));
            if (isDuplicated) throw DuplicateDataException(getEntity(dto));
        }

        val document:T = repository.save(dto);

        return document;
    }
    fun update(id:Int, dto:T):T {
        val document:T = this.findById(id);
        val entity:Class<*>?= getEntity(document).entity;

        for (property in entity!!.kotlin.memberProperties) {
            val value = (property as KProperty1<T, *>).call(dto);
            if (value != null) {
                try {
                    val key = entity.getDeclaredField(property.name);
                    key.isAccessible = true;
                    key.set(document, value);
                } catch (error: RuntimeException) {
                    throw UnprocessableEntityException(entity);
                }
            }
        }
        return repository.save(document)
    }
    fun delete(id:Int): Candidate {
        val dto:T = this.findById(id);
        repository.deleteById(id);

        return dto as ResponseEntity<Void>;
    }
    private fun getEntity(entity:T): EntitiesEnum{
        val entitiesEnum:EntitiesEnum? = EntitiesEnum.fromClasse(entity.javaClass);
        if (entitiesEnum == null) throw UnprocessableEntityException(entity.javaClass);

        return entitiesEnum;
    }
}
// coisas para usuar futuramente ou comentários
//    private fun getId(objeto: T):Any? {
//        val propriedades = objeto::class.members.filterIsInstance<KProperty1<T, *>>();
//        for (propriedade in propriedades) {
//            if (propriedade.name == "Id") return propriedade.get(objeto);
//        }
//        return null;
//    }