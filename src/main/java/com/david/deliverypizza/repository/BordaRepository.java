package com.david.deliverypizza.repository;

import com.david.deliverypizza.model.Borda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BordaRepository extends JpaRepository<Borda, Long> {


}
