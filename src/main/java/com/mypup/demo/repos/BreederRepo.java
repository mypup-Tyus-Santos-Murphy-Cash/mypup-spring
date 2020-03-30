package com.mypup.demo.repos;

public interface BreederRepo extends JpaRepository<Breeder, String>{
    Breeder findByUsername(String breeders);
}
