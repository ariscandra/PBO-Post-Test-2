package service;

import model.Pet;
import java.util.ArrayList;
import java.util.List;

public class PetService {
    private ArrayList<Pet> pets = new ArrayList<>();
    private int hitungId = 1;

    // tambah
    public void tambahPet(Pet pet) {
        pet.setId(String.valueOf(hitungId++));
        pets.add(pet);
    }

    // daftar
    public List<Pet> getDaftar() {
        return pets;
    }

    // apdet
    public boolean updatePet(String id, Pet newPet) {
        for (Pet pet : pets) {
            if (pet.getId().equals(id)) {
                pet.setNama(newPet.getNama());
                pet.setJenis(newPet.getJenis());
                pet.setUmur(newPet.getUmur());
                pet.setPemilik(newPet.getPemilik());
                return true;
            }
        }
        return false;
    }

    // apus
    public boolean hapusPet(String id) {
        return pets.removeIf(pet -> pet.getId().equals(id));
    }

    // cari
    public List<Pet> cariPet(String keyword) {
        List<Pet> hasil = new ArrayList<>();
        for (Pet pet : pets) {
            if (pet.getId().toLowerCase().contains(keyword.toLowerCase()) ||
            	pet.getNama().toLowerCase().contains(keyword.toLowerCase()) ||
                pet.getJenis().toLowerCase().contains(keyword.toLowerCase()) ||
                pet.getPemilik().toLowerCase().contains(keyword.toLowerCase())) {
                hasil.add(pet);
            }
        }
        return hasil;
    }
}