package com.david.deliverypizza.repository;

import com.david.deliverypizza.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario> {

    @Transactional(readOnly = true)
    Usuario findByEmail(String email);


}