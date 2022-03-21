package com.softtech.softtechspringboot.Service.EntityService;

import com.softtech.softtechspringboot.Entity.BaseEntity.BaseAdditionalFields;
import com.softtech.softtechspringboot.Entity.BaseEntity.BaseEntity;
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

    @Autowired
    /** Circular dependency*/
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

    public Optional<Entity> findById(Long id) {
        return dao.findById(id);
    }

    public Entity getByIdWithControl(Long id) {
        Entity entity = dao.findById(id).orElseThrow(() -> new EntityNotFoundExceptions(GeneralErrorMessage.ENTITY_NOT_FOUND, className));
        return entity;
    }

    public void entityExistValidation(Long id) {
        Optional<Entity> entityOptional = findById(id);
        Entity entity;
        if (!entityOptional.isPresent()) {
            throw new EntityNotFoundExceptions(GeneralErrorMessage.ENTITY_NOT_FOUND, className);
        }
    }

    public void delete(Entity entity) {
        dao.delete(entity);
    }

    public Long getCurrentCustomerId() {
        Long currentCustomerId = authenticationService.getCurrentUserId();
        return currentCustomerId;
    }

    private void setAdditionalFields(Entity entity) {

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();

        Long currentCustomerId = getCurrentCustomerId();

        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
            entity.setBaseAdditionalFields(baseAdditionalFields);
        }

        if (entity.getId() == null) {
            baseAdditionalFields.setCreateDate(new Date());
            baseAdditionalFields.setCreatedBy(currentCustomerId);
        }

        baseAdditionalFields.setUpdateDate(new Date());
        baseAdditionalFields.setUpdatedBy(currentCustomerId);
    }

}
