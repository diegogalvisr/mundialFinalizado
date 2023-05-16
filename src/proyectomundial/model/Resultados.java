/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectomundial.model;

/**
 *
 * @author juand
 */
public class Resultados {

    String grupo;
    String local;
    String visitante;
    String continenteL;
    String continenteV;
    String golesL;
    String golesV;

    public Resultados() {

    }

    public Resultados(String grupo, String local, String visitante, String continenteL, String continenteV, String golesL, String golesV) {
        this.grupo = grupo;
        this.local = local;
        this.visitante = visitante;
        this.continenteL = continenteL;
        this.continenteV = continenteV;
        this.golesL = golesL;
        this.golesV = golesV;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getContinenteL() {
        return continenteL;
    }

    public void setContinenteL(String continenteL) {
        this.continenteL = continenteL;
    }

    public String getContinenteV() {
        return continenteV;
    }

    public void setContinenteV(String continenteV) {
        this.continenteV = continenteV;
    }

    public String getGolesL() {
        return golesL;
    }

    public void setGolesL(String golesL) {
        this.golesL = golesL;
    }

    public String getGolesV() {
        return golesV;
    }

    public void setGolesV(String golesV) {
        this.golesV = golesV;
    }

}
