package ro.thales.mytools.registryapp.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailChecker implements Predicate<String> {
    @Override
    public boolean test(String s) {
        if(s.contains("@")) return true;
        else return false;
    }
}
