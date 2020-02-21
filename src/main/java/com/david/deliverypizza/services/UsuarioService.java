package com.david.deliverypizza.services;

import com.david.deliverypizza.model.Usuario;
import com.david.deliverypizza.repository.UsuarioRepository;
import com.david.deliverypizza.specification.UsuarioSpecification;
import com.david.deliverypizza.validation.SpecificationValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository repository;
    private final ApplicationContext context;


    public UsuarioService(UsuarioRepository repository, ApplicationContext context) {
        this.repository = repository;
        this.context = context;
    }

    public Usuario save(Usuario usuario){
        SpecificationValidator.create(context)
                .addSpecification(UsuarioSpecification.checkEmailDuplicado(repository))
                .validateWithException(usuario);


        return repository.save(usuario);

    }
}