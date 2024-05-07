package org.mashirocl.viewer.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mashirocl.visualize.SimplifiedCommit;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mashirocl@gmail.com
 * @since 2024/05/05 13:10
 */
@Slf4j
@Service
public class SimplifiedCommitService {
    public List<SimplifiedCommit> getAllSimplifiedCommits(){
        try{
            File file = new File(getClass().getClassLoader().getResource("mbassador.json").getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, new TypeReference<List<SimplifiedCommit>>() {});
        }
        catch (IOException e){
            log.error(e.getMessage(), e);
        }
        return new LinkedList<>();
    }

    public SimplifiedCommit getSingleSimplifiedCommit(String sha1){
        List<SimplifiedCommit> simplifiedCommits = getAllSimplifiedCommits();
        for(SimplifiedCommit simplifiedCommit: simplifiedCommits){
            if(simplifiedCommit.getSha1().equals(sha1)){
                log.info("simplifiedCommit: {}", simplifiedCommit);
                return simplifiedCommit;
            }
        }
        return null;
    }
}
