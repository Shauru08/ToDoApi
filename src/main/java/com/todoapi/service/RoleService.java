package com.todoapi.service;

import com.todoapi.domain.dto.role.request.RoleCreateRequest;
import com.todoapi.domain.dto.role.request.RoleUpdateRequest;
import com.todoapi.domain.dto.role.response.*;
import com.todoapi.domain.entity.Role;
import com.todoapi.domain.enums.ErrorCodes;
import com.todoapi.interfaces.service.RoleServiceInterface;
import com.todoapi.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleServiceInterface {

    private static final Logger logger = LogManager.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleCreateResponse addRole(RoleCreateRequest roleCreateRequest) {
        RoleCreateResponse roleCreateResponse = new RoleCreateResponse();
        try {
            logger.info("addRole [Init]");
            logger.info(String.format("Request: {}", roleCreateRequest));

            // Crear una nueva entidad Role
            Role newRole = new Role(roleCreateRequest);

            // Guardar la entidad en la base de datos
            roleRepository.save(newRole);

            // Respuesta de éxito
            roleCreateResponse.setErrorResponse(newRole.getId(), ErrorCodes.SUCCESS.getCode(), "Role added successfully");

        } catch (Exception ex) {
            logger.error("An unknown error occurred while adding the new role, Exception {}", ex.getMessage(), ex);
            roleCreateResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while adding the new role");
        } finally {
            logger.info("Response {}", roleCreateResponse);
            logger.info("addRole [Fin]");
        }
        return roleCreateResponse;
    }

    @Override
    public RoleUpdateResponse updateRole(RoleUpdateRequest roleUpdateRequest, Long roleId) {
        RoleUpdateResponse roleUpdateResponse = new RoleUpdateResponse();
        try {
            logger.info("updateRole [Init]");
            logger.info("Request: {}, RoleId: {}", roleUpdateRequest, roleId);

            // Buscar el role por id
            Optional<Role> foundRoleOptional = roleRepository.findById(roleId);

            if (foundRoleOptional.isPresent()) {
                Role roleToUpdate = foundRoleOptional.get();
                logger.info("Role found in the database: {}", roleToUpdate);

                if (roleUpdateRequest.getName() != null) {
                    roleToUpdate.setName(roleUpdateRequest.getName());
                }

                // Actualizar el role
                roleRepository.save(roleToUpdate);

                // Respuesta de éxito
                roleUpdateResponse.setErrorResponse(roleToUpdate, ErrorCodes.SUCCESS.getCode(), "Role updated successfully");
            } else {
                roleUpdateResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Role not found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while updating the role. Exception: {}", ex.getMessage(), ex);
            roleUpdateResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while updating the role.");
        } finally {
            logger.info("Response: {}", roleUpdateResponse);
            logger.info("updateRole [Fin]");
        }
        return roleUpdateResponse;
    }

    @Override
    public RoleDeleteResponse deleteRole(Long roleId) {
        RoleDeleteResponse roleDeleteResponse = new RoleDeleteResponse();
        try {
            logger.info("deleteRole [Init]");
            logger.info("RoleId: {}", roleId);

            // Verificar si el role existe
            if (roleRepository.existsById(roleId)) {
                logger.info("Role found, deleting...");

                // Eliminar el role
                roleRepository.deleteById(roleId);

                // Respuesta de éxito
                roleDeleteResponse.setErrorResponse(roleId, ErrorCodes.SUCCESS.getCode(), "Role deleted successfully");
            } else {
                roleDeleteResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Role not found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while deleting the role. Exception: {}", ex.getMessage(), ex);
            roleDeleteResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while deleting the role.");
        } finally {
            logger.info("Response: {}", roleDeleteResponse);
            logger.info("deleteRole [Fin]");
        }
        return roleDeleteResponse;
    }

    @Override
    public RoleListByIdResponse getRoleById(Long roleId) {
        RoleListByIdResponse roleListByIdResponse = new RoleListByIdResponse();
        try {
            logger.info("getRoleById [Init]");
            logger.info("RoleId: {}", roleId);

            // Llamar al repositorio para obtener los datos del rol por su ID
            Optional<Role> roleFound = roleRepository.findById(roleId);

            if (roleFound.isPresent()) {
                // Respuesta exitosa: el rol fue encontrado
                roleListByIdResponse.setErrorResponse(roleFound.get(), ErrorCodes.SUCCESS.getCode(), "The role was found");
            } else {
                // Respuesta de error: el rol no fue encontrado
                roleListByIdResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "Role Was Not Found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while looking for the role in the database. Exception: {}", ex.getMessage(), ex);
            roleListByIdResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while looking for the role in the database.");
        } finally {
            logger.info("Response: {}", roleListByIdResponse);
            logger.info("getRoleById [Fin]");
        }
        return roleListByIdResponse;
    }

    @Override
    public RoleListAllResponse getAllRoles() {
        RoleListAllResponse roleListAllResponse = new RoleListAllResponse();
        try {
            logger.info("getAllRoles [Init]");

            // Llamar al repositorio para listar todos los roles
            List<Role> allRoles = roleRepository.findAll();

            // Configurar la respuesta en base a si se encontraron roles o no
            if (allRoles.size() > 0) {
                roleListAllResponse.setErrorResponse(allRoles, ErrorCodes.SUCCESS.getCode(), "All the roles were gathered correctly");
            } else {
                roleListAllResponse.setErrorResponse(null, ErrorCodes.NOT_FOUND.getCode(), "No roles were found");
            }
        } catch (Exception ex) {
            logger.error("An unknown error occurred while trying to collect the roles. Exception {}", ex.getMessage(), ex);
            roleListAllResponse.setErrorResponse(null, ErrorCodes.EXCEPTION.getCode(), "An unknown error occurred while trying to collect the roles.");
        } finally {
            logger.info("Response: {}", roleListAllResponse);
            logger.info("getAllRoles [Fin]");
        }
        return roleListAllResponse;
    }

}
