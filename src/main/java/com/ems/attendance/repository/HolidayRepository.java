package com.ems.attendance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ems.attendance.entity.HolidayDetail;

@Repository
public interface HolidayRepository extends CrudRepository<HolidayDetail, Long> {

    HolidayDetail findByHdate(Date date);
    List<HolidayDetail> findBycountry(String country);

    @Transactional
    void deleteByHdate(long date);

}	