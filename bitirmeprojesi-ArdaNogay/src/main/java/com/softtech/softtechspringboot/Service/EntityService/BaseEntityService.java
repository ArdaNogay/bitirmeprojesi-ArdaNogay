package com.softtech.softtechspringboot.Service.EntityService;

import com.softtech.softtechspringboot.entity.BaseEntity.BaseAdditionalFields;
import com.softtech.softtechspringboot.entity.BaseEntity.BaseEntity;
import com.softtech.softtechspringboot.Enum.ErrorEnums.GeneralErrorMessage;
import com.softtech.softtechspringboot.Exception.EntityNotFoundExceptions;
import com.softtech.softtechspringboot.Security.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<Entity extends BaseEntity, Dao extends JpaRepository<Entity, Long>> {

    private final Dao dao;

    private AuthenticationService authenticationService;

    private final String className = ((Class<Entity>) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0]).getSimpleName();

    @Autowired  /** Circular dependency*/
    public void setAuthenticationService(@Lazy AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public Dao getDao() {
        return dao;
    }

    public Entity save(Entity entity) {
        setAdditionalFields(entity);
        return dao.save(entity);
    }

    public List<Entity> findAll() {
        List<Entity> entityList = dao.findAll();
        if (entityList.isEmpty()) {
            throw new EntityNotFoundExceptions(GeneralErrorMessage.ENTITIES_NOT_FOUND);
        }
        return entityList;
    }

    public Entity getByIdWithControl(Long id) {
        Entity entity = dao.findById(id).orElseThrow(() -> new EntityNotFoundExceptions(GeneralErrorMessage.ENTITY_NOT_FOUND, className));
        return entity;
    }

    public void deleteByIdWithControl(Long id) {
        Entity entity = getByIdWithControl(id);
        dao.delete(entity);
    }

    public void validateEntityExist(Long id) {
        Optional<Entity> entityOptional = dao.findById(id);
        if (!entityOptional.isPresent()) {
            throw new EntityNotFoundExceptions(GeneralErrorMessage.ENTITY_NOT_FOUND, className);
        }
    }

    public Long getCurrentUserId() {
        Long currentUserId = authenticationService.getCurrentUserId();
        return currentUserId;
    }

    private void setAdditionalFields(Entity entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentUserId = getCurrentUserId();

        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null) {
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentUserId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentUserId);
    }

}
