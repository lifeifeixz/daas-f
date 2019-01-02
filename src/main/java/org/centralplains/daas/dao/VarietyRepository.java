package org.centralplains.daas.dao;


import org.centralplains.daas.beans.Variety;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by flysLi on 2018/12/28.
 */
@Repository
public interface VarietyRepository extends JpaRepository<Variety, Integer> {

    Variety findByCode(String code);
}
