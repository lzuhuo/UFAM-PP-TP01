package services;

import java.util.ArrayList;

import business.Categoria.MotorDAO;
import model.Categoria.Motor;

public class MotorService {
    public ArrayList<Motor> listarMotor(){
        ArrayList<Motor> motores;
        MotorDAO motorDAO = new MotorDAO();
        motores = motorDAO.listarMotor();
        return motores;
    }
}
