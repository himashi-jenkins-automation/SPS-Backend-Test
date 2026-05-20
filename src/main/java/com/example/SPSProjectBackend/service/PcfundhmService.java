package com.example.SPSProjectBackend.service;

import com.example.SPSProjectBackend.model.Pcfundhm;
import com.example.SPSProjectBackend.repository.PcfundhmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PcfundhmService {

    @Autowired
    private PcfundhmRepository repository;

    public List<Pcfundhm> getAllFunds() {
        return repository.findAll();
    }

    public List<String> getAllFundSources() {
        return repository.findAllFundSources();
    }
}
