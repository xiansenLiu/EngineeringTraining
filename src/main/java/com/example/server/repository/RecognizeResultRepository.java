package com.example.server.repository;

import com.example.server.domain.RecognizeResult;
import org.springframework.data.repository.CrudRepository;

/**
 * Author       xinliu
 * Date         7/2/17
 * Time         10:28 AM
 */
public interface RecognizeResultRepository extends CrudRepository<RecognizeResult, Long> {
    RecognizeResult findByMd5(String md5);
}
