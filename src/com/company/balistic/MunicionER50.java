package com.company.balistic;


public class MunicionER50 extends Tools implements Municion {

    public int carga;
    public int cuadros;
    public boolean isPossible;

    public boolean isLG = true;

    public MunicionER50() {

        setRasante(true);
        setCuadros(2);
        double elevacion = 300;
        double distancia = 11000;
        double azimut = 900;
        double intervalo = 0;
        double intervaloDMP_Arma = -500;
        int carga=seleccionCargaAutomatica(distancia,intervalo);
/*
        setCarga(seleccionCargaAutomatica(distancia,intervalo));

        System.out.println("getCarga() = " + getCarga());
        System.out.println("elevacion = " + elevacion);
        System.out.println("distancia = " + distancia);
        System.out.println("azimut = " + azimut);
        System.out.println("intervalo = " + intervalo);
        System.out.println("intervaloDMP_Arma = " + intervaloDMP_Arma);
        System.out.println(" ");
        System.out.println("Carga automatica: "+seleccionCargaAutomatica(distancia,intervalo));

        System.out.println("getTA_LineaMeteorologica(elevacion) = " + getTA_LineaMeteorologica(elevacion,carga));
        System.out.println("getTBDistanciaPorComplementario(distancia,) = " + getTBDistanciaPorComplementario(distancia,intervalo,carga));
        System.out.println("getC() = " + getC(elevacion));
        System.out.println("getTipoMunicion() = " + getTipoMunicion());
        System.out.println("getCuadros() = " + getCuadros());
        System.out.println("getCuadrosStandard() = " + getCuadrosStandard());

        System.out.println("getTG_AnguloComp_Mas(distancia) = " + getTG_AnguloComp_Mas(distancia,carga));
        System.out.println("getTG_AnguloComp_Menos(distancia) = " + getTG_AnguloComp_Menos(distancia,carga));
        System.out.println("getTC_ComponenteVientoCabeza() = " + getTC_ComponenteVientoCabeza(azimut));
        System.out.println("getTC_ComponenteVientoCruzado(azimut) = " + getTC_ComponenteVientoCruzado(azimut));
        System.out.println("getTD_CorreccionDensidad(70) = " + getTD_CorreccionDensidad(intervaloDMP_Arma));
        System.out.println("getTD_CorreccionTemperatura(70) = " + getTD_CorreccionTemperatura(intervaloDMP_Arma));
        System.out.println("getTE_Propellanttemperature(22) = " + getTE_Propellanttemperature(22,carga));
        System.out.println("getTFi1_DistanciaApuntada(elevacion) = " + getTFi1_DistanciaApuntada(elevacion,carga));
        System.out.println("getTFi2_Elevacion(distancia) = " + getTFi2_Elevacion(distancia,carga));
        System.out.println("getTFi4_HeighExplosure10m(distancia) = " + getTFi4_HeighExplosure10m(distancia,carga));
        System.out.println("getTFi5_ChangeRangeFor1Mil(distancia) = " + getTFi5_ChangeRangeFor1Mil(distancia,carga));
        System.out.println("getTFi6_Fork(distancia) = " + getTFi6_Fork(distancia,carga));
        System.out.println("getTFi7_TiempoVuelo(distancia) = " + getTFi7_TiempoVuelo(distancia,carga));
        System.out.println("getTFi8_Drift(distancia) = " + getTFi8_Drift(distancia,carga));
        System.out.println("getTFi9_CrossWind(distancia) = " + getTFi9_CrossWind(distancia,carga));
        System.out.println("getTFii10_muzzleVelDec(distancia) = " + getTFii10_muzzleVelDec(distancia,carga));
        System.out.println("getTFii11_muzzleVelInc(distancia) = " + getTFii11_muzzleVelInc(distancia,carga));
        System.out.println("getTFii12_VientoLongitudinal1KNCabeza(distancia) = " + getTFii12_VientoLongitudinal1KNCabeza(distancia,carga));
        System.out.println("getTFii13_VientoLongitudinal1KNCola(distancia) = " + getTFii13_VientoLongitudinal1KNCola(distancia,carga));
        System.out.println("getFii14_TempAireDec(distancia) = " + getFii14_TempAireDec(distancia,carga));
        System.out.println("getFii15_TempAireInc(distancia) = " + getFii15_TempAireInc(distancia,carga));
        System.out.println("getFii16_DensAireDec(distancia) = " + getFii16_DensAireDec(distancia,carga));
        System.out.println("getFii17_DensAireInc(distancia) = " + getFii17_DensAireInc(distancia,carga));
        System.out.println("getFii18_BoxWeitghDec(distancia) = " + getFii18_BoxWeitghDec(distancia,carga));
        System.out.println("getFii19_BoxWeitghInc() = " + getFii19_BoxWeitghInc(distancia,carga));
        System.out.println("getGOrdenadaMax_(elevacion) = " + getGOrdenadaMax_(elevacion,carga));
        System.out.println("getTG_AnguloComp_Menos(distancia) = " + getTG_AnguloComp_Menos(distancia,carga));
        System.out.println("getTG_AnguloComp_Mas(distancia) = " + getTG_AnguloComp_Mas(distancia,carga));
        System.out.println("getH_DistanciaPorRotacionTierra(distancia,azimut,10) = " + getH_DistanciaPorRotacionTierra(distancia, azimut, 10,carga));
*/
    }

    boolean rasante = true;

    @Override
    public boolean isRasante() {
        return rasante;
    }

    @Override
    public void setRasante(boolean rasante) {
        this.rasante = rasante;
    }

    @Override
    public void setCarga(int carga) {
        if (carga > 2) {
            System.out.println("ERROR:La carga no puede ser superior a 2 en ER50BB");
            return;
        }
        this.carga = carga;
    }

    @Override
    public int getCarga() {
        return this.carga;
    }

    @Override
    public int getMuzzleVelocity() {
        if (getCarga() == 1) return 535;
        else if (getCarga() == 2) return 630;
        else return 0;
    }

    @Override
    public void setCuadros(int cuadros) {
        if (cuadros > 4) {
            System.out.println("ATENCION: Los cuadros no pueden ser superior a 4");
            return;
        }
        this.cuadros = cuadros;
    }

    @Override
    public int getCuadros() {
        return this.cuadros;
    }

    @Override
    public int getCuadrosStandard() {
        return 2;
    }

    @Override
    public double getTemperaturaPropelenteStandard() {
        return 21;
    }

    @Override
    public String getTipoMunicion() {
        return "105 mm - HE ER/50 BB ";
    }

    @Override
    public double getWeitgh() {
        return 15.85;
    }///01cuad: 15.7 - - - 02 cuad: 15.85 ---- 03 cuad:16

    @Override
    public double getC(double elevation) {

        switch (carga) {
            case 1:
                return -6.08606830927E-19 * potencia(elevation, 6)
                        + 3.02605106658742E-15 * potencia(elevation, 5)
                        - 6.04316176206805E-12 * potencia(elevation, 4)
                        + 6.06908101888823E-09 * potencia(elevation, 3)
                        - 2.88410878316137E-06 * potencia(elevation, 2)
                        + 0.0000593282822239608 * elevation + 0.60886829508525;
            case 2:
                return -1.397913635925E-18 * potencia(elevation, 6)
                        + 6.27353124050086E-15 * potencia(elevation, 5)
                        - 1.12111428106618E-11 * potencia(elevation, 4)
                        + 1.01025716862588E-08 * potencia(elevation, 3)
                        - 4.51036555371932E-06 * potencia(elevation, 2)
                        + 0.000351971096050932 * elevation + 0.637860468619722;
            default:
                return 0;
        }

    }

    @Override
    public double getDeltaCVelInicial(double elevation, double cambioVel, int carga) {
        if (carga==1)return 2.05836878282816*Math.pow(elevation,-1.14644580115341)*cambioVel;
        else if (carga==2)return 0.776305809337931*Math.pow(elevation,-1.02975157587539)*cambioVel;
        else return 0;
    }

    @Override
    public double getDeltaCVientoLongitudinal(double elevation, double vientoLong, int carga) {
       if (carga==1)return  0.0892710792454866*Math.pow(elevation,-0.78470762555825)*vientoLong;
       else if (carga==2) {
           return (1.802252309E-20 * potencia(elevation, 6) - 6.4469010654339E-17 * potencia(elevation, 5)
                   + 8.61167128537228E-14 * potencia(elevation, 4) - 5.27925617784405E-11 * potencia(elevation, 3)
                   + 1.47997641371525E-08 * potencia(elevation, 2) - 2.22397676129298E-06 * potencia(elevation, 1)
                   + 0.00106882112549757) * vientoLong;
       }
       else return 0;
    }

    @Override
    public double getDeltaCTemperatura(double elevation, double porcTemp, int carga) {
        if (carga==1) {
            return (4.2503030701E-20*potencia(elevation,6 )- 1.76335139301141E-16*potencia(elevation,5 )+ 2.73945355569639E-13*potencia(elevation,4 )
                    - 1.88667096849364E-10*potencia(elevation,3 )+ 4.58816800711096E-08*potencia(elevation,2 )+ 4.77835620823315E-06*potencia(elevation,1 )
                    - 0.00150189487380929)*porcTemp;
        }
        else if (carga==2) {
            return (1.23800308335E-19*potencia(elevation,6 )- 5.18488509041524E-16*potencia(elevation,5 )+ 8.34171870404198E-13*potencia(elevation,4 )
                    - 6.3691708956948E-10*potencia(elevation,3 )+ 2.25140743044239E-07*potencia(elevation,2 )- 0.0000268418981057071*potencia(elevation,1 )
                    - 0.000459005704901506)*porcTemp ;
        }
        else return 0;
    }

    @Override
    public double getDeltaCDensidad(double elevation, double porcDens, int carga) {
        if (carga==1) {
            return (-1.1334759952E-19*potencia(elevation,6 )+ 4.63025161881247E-16*potencia(elevation,5 )- 7.32592402809026E-13*potencia(elevation,4 )
                    + 5.68155786473639E-10*potencia(elevation,3 )- 2.29716900417443E-07*potencia(elevation,2 )+ 0.0000515997851451726*potencia(elevation,1 )
                    - 0.0090133852719471)*porcDens;
        }
        else if (carga==2) {
            return (1.52439011381E-19*potencia(elevation,6 )- 5.6401100946577E-16*potencia(elevation,5 )+ 7.59341473922339E-13*potencia(elevation,4 )
                    - 4.30162254733577E-10*potencia(elevation,3 )+ 6.76819738836318E-08*potencia(elevation,2 )+ 0.0000231192402095116*potencia(elevation,1 )
                    - 0.010129989059556)*porcDens;
        }
        else return 0;
    }

    @Override
    public double getDeltaCCuadros(double elevation, double inc_O_Dec, int carga) {
        if (carga==1) {
            return (1.66319030958E-19*potencia(elevation,6 )- 5.27708682773602E-16*potencia(elevation,5 )+ 4.70875289902222E-13*potencia(elevation,4 )
                    + 9.24326181814196E-11*potencia(elevation,3 )- 3.48457569149927E-07*potencia(elevation,2 )+ 0.000175157007226108*potencia(elevation,1 )
                    - 0.0287052526232119)*inc_O_Dec;
        }
        else if (carga==2) {
            return (-7.300715169965E-18*potencia(elevation,6 )+ 3.01089981141524E-14*potencia(elevation,5 )- 4.79801025087052E-11*potencia(elevation,4 )
                    + 3.70276579258879E-08*potencia(elevation,3 )- 0.0000141813940851199*potencia(elevation,2 )+ 0.00246683331073299*potencia(elevation, 1)
                    - 0.147356713538614)*inc_O_Dec;
        }
        else return 0;
    }

    @Override
    public int seleccionCargaAutomatica(double distancia, double intervalo) {
        int carga = 1;
        if (isPossible(distancia, intervalo, carga)) return carga;
        else if (isPossible(distancia, intervalo, carga + 1)) return carga + 1;
        else return 0;
    }

    @Override
    public boolean isPossible(double distancia, double intervalo, int carga) {
        double anguloSituacion = calculaAnguloCatetosMilesimas(intervalo, distancia);
        double anguloComplementario=0;
        if (intervalo>=0) anguloComplementario=getTG_AnguloComp_Mas(distancia,carga)*anguloSituacion;
        else anguloComplementario=getTG_AnguloComp_Menos(distancia,carga)*anguloSituacion;
        double elevacionPorDistancia=getTFi2_Elevacion(distancia, carga);
        double elevacionRequerida = elevacionPorDistancia+anguloSituacion+anguloComplementario;

        if (rasante == true) {
            if (elevacionRequerida >= 750) {
                isPossible = false;
            } else if (elevacionRequerida < 800 && elevacionRequerida > 80) {
                isPossible = true;
            }
        } else if (rasante == false) {
            if (elevacionRequerida <= 860) {
                isPossible = false;
            } else if (elevacionRequerida > 800 && elevacionRequerida < 1200) {
                isPossible = true;
            }
        }
        return isPossible;
    }

    @Override
    public int getTA_LineaMeteorologica(double elevacion, int carga) {
        int lineaMet = 0;
        if (carga == 1) {
            if (isRank(elevacion, 89, -100))lineaMet = 0;
            else if (isRank(elevacion, 175.1, 89.1)) lineaMet = 1;
            else if (isRank(elevacion, 268.8, 175.2)) lineaMet = 2;
            else if (isRank(elevacion, 361.7, 268.9)) lineaMet = 3;
            else if (isRank(elevacion, 442.1, 361.8)) lineaMet = 4;
            else if (isRank(elevacion, 549.5, 442.2)) lineaMet = 5;
            else if (isRank(elevacion, 678.7, 549.6)) lineaMet = 6;
            else if (isRank(elevacion, 800.2, 678.8)) lineaMet = 7;
            else if (isRank(elevacion, 920.1, 800.3)) lineaMet = 8;
            else if (isRank(elevacion, 1113, 920.2)) lineaMet = 9;
            else if (isRank(elevacion, 1275, 1113.1)) lineaMet = 10;
            else System.out.println("Elevacion fuera de rango para linea " +
                        "meteorologica Munición: " + getTipoMunicion() + "  carga: " + getCarga());
        } else if (carga == 2) {
            if (isRank(elevacion, 76.2, -90)) lineaMet = 0;
            else if (isRank(elevacion, 150.4, 76.3)) lineaMet = 1;
            else if (isRank(elevacion, 231.5, 150.5)) lineaMet = 2;
            else if (isRank(elevacion, 312.1, 231.6)) lineaMet = 3;
            else if (isRank(elevacion, 381.9, 312.2)) lineaMet = 4;
            else if (isRank(elevacion, 475.4, 382)) lineaMet = 5;
            else if (isRank(elevacion, 587.6, 475.5)) lineaMet = 6;
            else if (isRank(elevacion, 691.4, 587.7)) lineaMet = 7;
            else if (isRank(elevacion, 790.5, 691.5)) lineaMet = 8;
            else if (isRank(elevacion, 936.9, 790.6)) lineaMet = 9;
            else if (isRank(elevacion, 1148.6, 937)) lineaMet = 10;
            else if (isRank(elevacion, 1275, 1148.7)) lineaMet = 11;
            else System.out.println("Elevacion fuera de rango para linea meteorologica "
                        + "Munición: " + getTipoMunicion() + "carga: " + getCarga()+"elevacion: "+elevacion);
        }
        return lineaMet;
    }

    @Override
    public double getTBDistanciaPorComplementario(double distancia, double intervalo, int carga) {
        double anguloSituacion = calculaAnguloCatetosMilesimas(intervalo, distancia);
        double anguloComplementario = 0;
        double situacion = 0;
        double distanciaPorIntervalo;

        if (intervalo >= 0) {
            anguloComplementario = anguloSituacion * getTG_AnguloComp_Mas(distancia, carga);
        } else {
            anguloComplementario = anguloSituacion * getTG_AnguloComp_Menos(distancia, carga);
        }
        situacion = anguloSituacion + anguloComplementario;
        distanciaPorIntervalo = situacion * getTFi5_ChangeRangeFor1Mil(distancia, carga);

        return distanciaPorIntervalo;
    }

    @Override
    public double getTG_AnguloComp_Menos(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -2.073873E-24 * potencia(distancia, 6)
                            + 7.2693597331E-20 * potencia(distancia, 5)
                            - 9.65268676103085E-16 * potencia(distancia, 4)
                            + 5.88053649615837E-12 * potencia(distancia, 3)
                            - 1.65190826620885E-08 * potencia(distancia, 2)
                            + 0.0000158081177183966 * distancia
                            - 0.00113684226781174;
                } else {
                    return 3.08333333334255E-15 * potencia(distancia, 4)
                            - 1.37833333333785E-10 * potencia(distancia, 3)
                            + 2.31841666667508E-06 * potencia(distancia, 2)
                            - 0.0173286666667409 * distancia
                            + 49.5640000002658;
                }

            case 2:
                if (isRasante()) {
                    return -1.007376E-24 * potencia(distancia, 6)
                            + 4.3797292298E-20 * potencia(distancia, 5)
                            - 7.19298794308346E-16 * potencia(distancia, 4)
                            + 5.42635954252608E-12 * potencia(distancia, 3)
                            - 1.85380445799849E-08 * potencia(distancia, 2)
                            + 0.0000222052531104744 * distancia
                            - 0.00281108564192323;
                } else {
                    return 6.08333333205E-19 * potencia(distancia, 5)
                            - 4.1874999990904E-14 * potencia(distancia, 4)
                            + 1.15287499973732E-09 * potencia(distancia, 3)
                            - 0.0000158571249961542 * potencia(distancia, 2)
                            + 0.108938516638184 * distancia
                            - 297.947999914536;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getTG_AnguloComp_Mas(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 5.996589E-24 * potencia(distancia, 6)
                            - 2.15098865091E-19 * potencia(distancia, 5)
                            + 2.89874312549055E-15 * potencia(distancia, 4)
                            - 1.79058152516292E-11 * potencia(distancia, 3)
                            + 5.00536984000301E-08 * potencia(distancia, 2)
                            - 0.0000486096601233221 * distancia
                            + 0.00368534592752212;
                } else {
                    return -9.50000000004272E-15 * potencia(distancia, 4)
                            + 4.2916666666878E-10 * potencia(distancia, 3)
                            - 7.27150000003944E-06 * potencia(distancia, 2)
                            + 0.0546828333336651 * distancia
                            - 154.976000001066;
                }

            case 2:
                if (isRasante()) {
                    return 3.563752E-24 * potencia(distancia, 6)
                            - 1.58376321721E-19 * potencia(distancia, 5)
                            + 2.63720843542681E-15 * potencia(distancia, 4)
                            - 2.01306624050612E-11 * potencia(distancia, 3)
                            + 6.90739848438612E-08 * potencia(distancia, 2)
                            - 0.0000837380974441082 * distancia
                            + 0.0107054410021021;
                } else {
                    return -2.983333333137E-18 * potencia(distancia, 5)
                            + 2.07208333318275E-13 * potencia(distancia, 4)
                            - 5.74766666620961E-09 * potencia(distancia, 3)
                            + 0.0000795712916597956 * potencia(distancia, 2)
                            - 0.549729849948686 * distancia
                            + 1515.01699984698;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getTC_ComponenteVientoCruzado(double azimuth) {
        double angulo;
        angulo = convierteMilesimas_a_Grados(azimuth);
        return Math.sin(Math.toRadians(angulo));
    }

    @Override
    public double getTC_ComponenteVientoCabeza(double azimuth) {
        double angulo;
        angulo = convierteMilesimas_a_Grados(azimuth);
        return Math.cos(Math.toRadians(angulo));
    }

    @Override
    public double getTD_CorreccionTemperatura(double deltaH_Bat_DMP) {
        return 8.46393657690631E-08 * potencia(deltaH_Bat_DMP, 2)
                - 0.00231818758904767 * deltaH_Bat_DMP
                + 0.00770034843205725;
    }

    @Override
    public double getTD_CorreccionDensidad(double deltaH_Bat_DMP) {
        return 1.83385292499465E-07 * potencia(deltaH_Bat_DMP, 2)
                - 0.00947489737476903 * deltaH_Bat_DMP
                - 0.0168118466898832;
    }

    @Override
    public double getTE_Propellanttemperature(double temperature, int carga) {
        switch (carga) {
            case 1:
                return 0.4 * temperature - 8.4;
            case 2:
                return 0.3297 * temperature - 6.9021;
            default:
                return 0;
        }
    }

    @Override
    public double getTFi1_DistanciaApuntada(double elevacion, int carga) {

        switch (carga) {
            case 1:
                if (rasante) {
                    if (elevacion >= 750) return 14810;
                    return -1.63303634294892E-13 * potencia(elevacion, 6) + 4.8298631817899E-10 * potencia(elevacion, 5) - 5.97960607991534E-07 * potencia(elevacion, 4)
                            + 0.000393616221980734 * potencia(elevacion, 3) - 0.161479651306858 * potencia(elevacion, 2) + 57.6185689426248 * elevacion + 5.37301402846737;
                } else {
                    if (elevacion <= 860) return 14810;
                    return -0.0254121764480013 * potencia(elevacion, 2) + 40.8731424800483 * elevacion - 1547.03002539804;

                }
            case 2:
                if (rasante) {
                    if (elevacion >= 780) return 17710;
                    return -3.27443863146365E-13 * potencia(elevacion, 6) + 9.29248469594716E-10 * potencia(elevacion, 5) - 1.09122048616572E-06 * potencia(elevacion, 4)
                            + 0.000682315786288967 * potencia(elevacion, 3) - 0.259374101147841 * potencia(elevacion, 2) + 78.2407763312222 * elevacion + 18.9121899427846;
                } else {
                    if (elevacion <= 851) return 17710;
                    return 1.3266323193521E-15 * potencia(elevacion, 6) - 1.62876036163251E-11 * potencia(elevacion, 5) + 7.70865318307854E-08 * potencia(elevacion, 4)
                            - 0.000173810796985361 * potencia(elevacion, 3) + 0.169784463633747 * potencia(elevacion, 2) - 64.2474482643967 * elevacion + 22873.0623484674;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getTFi2_Elevacion(double distancia, int carga) {
        double elevacion = 0;

        if (distancia >= 14800 && carga == 1) {
            distancia = 14800;
            if (isRasante()) return 750;
            else return 860;

        } else if (distancia >= 17700 && carga == 2) {
            distancia = 17700;
            if (isRasante()) return 780;
            else return 851;
        } else {

            if (rasante) {
                while (getTFi1_DistanciaApuntada(elevacion + 100, carga) <= distancia) {
                    elevacion = elevacion + 100;
                    if (elevacion>750)return elevacion;
                }
                while (getTFi1_DistanciaApuntada(elevacion + 10, carga) <= distancia) {
                    elevacion = elevacion + 10;
                    if (elevacion>750)return elevacion;
                }
                while (getTFi1_DistanciaApuntada(elevacion + 1, carga) <= distancia) {
                    elevacion = elevacion + 1;
                    if (elevacion>750)return elevacion;
                }
                while (getTFi1_DistanciaApuntada(elevacion, carga) <= distancia) {
                    elevacion = elevacion + 0.1;
                    if (elevacion>750)return elevacion;
                }


            } else {
                elevacion = 1300;
                while (getTFi1_DistanciaApuntada(elevacion - 100, carga) < distancia) {
                    elevacion = elevacion - 100;
                }
                while (getTFi1_DistanciaApuntada(elevacion - 10, carga) < distancia) {
                    elevacion = elevacion - 10;
                }
                while (getTFi1_DistanciaApuntada(elevacion - 1, carga) < distancia) {
                    elevacion = elevacion - 1;
                }
                while (getTFi1_DistanciaApuntada(elevacion, carga) < distancia) {
                    elevacion = elevacion - 0.1;
                }

            }
        }
        return elevacion;
    }

    @Override
    public double getTFi4_HeighExplosure10m(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 5.6806E-24 * potencia(distancia, 6) - 3.03596653451E-19 * potencia(distancia, 5) + 6.49377216214079E-15 * potencia(distancia, 4)
                            - 7.10442187363908E-11 * potencia(distancia, 3) + 4.21753475028142E-07 * potencia(distancia, 2) - 0.00132953228640224 * distancia + 1.9777393314078;
                } else {
                    return 4.8070203E-23 * potencia(distancia, 6) - 3.371118995285E-18 * potencia(distancia, 5) + 9.80883349465441E-14 * potencia(distancia, 4)
                            - 1.51567171102658E-09 * potencia(distancia, 3) + 0.0000131176227898254 * potencia(distancia, 2) - 0.060289566439815 * distancia + 114.99341340627;
                }

            case 2:
                if (isRasante()) {
                    return 2.037743E-24 * potencia(distancia, 6) - 1.29216886456E-19 * potencia(distancia, 5) + 3.27849146855446E-15 * potencia(distancia, 4)
                            - 4.2524205533021E-11 * potencia(distancia, 3) + 2.99032330120786E-07 * potencia(distancia, 2) - 0.0011153991272243 * distancia + 1.95880439169865;
                } else {
                    return 0.03;
                }

            default:
                return 0;
        }
    }

    @Override
    public double getTFi5_ChangeRangeFor1Mil(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -1.2457219E-23 * potencia(distancia, 6) + 3.97436785116E-19 * potencia(distancia, 5) - 5.89122223822828E-15 * potencia(distancia, 4)
                            + 5.12693760499856E-11 * potencia(distancia, 3) - 2.51422265187808E-08 * potencia(distancia, 2) - 0.00562118911072469 * distancia + 57.7735341520024;
                } else {
                    return -2.53327675507057E-14 * potencia(distancia, 4) + 1.13695943137225E-09 * potencia(distancia, 3)
                            - 0.0000193219469700868 * potencia(distancia, 2) + 0.144782963597458 * distancia - 377.202391062972;
                }

            case 2:
                if (isRasante()) {
                    return -1.4561273E-23 * potencia(distancia, 6) + 6.73902742352E-19 * potencia(distancia, 5) - 1.26563690753105E-14 * potencia(distancia, 4)
                            + 1.1960415323553E-10 * potencia(distancia, 3) - 3.26239598671483E-07 * potencia(distancia, 2) - 0.00665530204546081 * distancia + 79.4723983052463;
                } else {
                    return -2.83012171247847E-14 * potencia(distancia, 4) + 1.54700364008789E-09 * potencia(distancia, 3) - 0.0000317659990613973 * potencia(distancia, 2)
                            + 0.287638641284066 * distancia - 936.969381266072;
                }

            default:
                return 0;
        }
    }

    @Override
    public double getTFi6_Fork(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 1.88008432E-22 * potencia(distancia, 6) - 7.280427775989E-18 * potencia(distancia, 5) + 1.05475701476983E-13 * potencia(distancia, 4)
                            - 6.99518417430569E-10 * potencia(distancia, 3) + 2.09552305803973E-06 * potencia(distancia, 2) - 0.0019135643072323 * distancia + 1.08051106144944;
                } else {
                    return 4.3408830229E-20 * potencia(distancia, 6) - 3.03034153467909E-15 * potencia(distancia, 5) + 8.78214612234099E-11 * potencia(distancia, 4)
                            - 1.35226125633255E-06 * potencia(distancia, 3) + 0.0116669234886836 * potencia(distancia, 2) - 53.4725757217761 * distancia + 101709.762968153;
                }

            case 2:
                if (isRasante()) {
                    return 1.0662455E-22 * potencia(distancia, 6) - 5.283012319366E-18 * potencia(distancia, 5) + 1.00048320607443E-13 * potencia(distancia, 4)
                            - 9.03050371304412E-10 * potencia(distancia, 3) + 3.97352528848509E-06 * potencia(distancia, 2) - 0.0074000451454353 * distancia + 5.14077714951988;
                } else {
                    return 4.3867496223E-20 * potencia(distancia, 6) - 3.72121401015413E-15 * potencia(distancia, 5) + 1.31037822195421E-10 * potencia(distancia, 4)
                            - 2.45169765577432E-06 * potencia(distancia, 3) + 0.0257042753924048 * potencia(distancia, 2) - 143.179170971626 * distancia + 331041.692844599;
                }

            default:
                return 0;
        }
    }

    @Override
    public double getTFi7_TiempoVuelo(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 4.0804021E-23 * potencia(distancia, 6) - 1.544651646035E-18 * potencia(distancia, 5) + 2.20300904215031E-14 * potencia(distancia, 4)
                            - 1.45019485820136E-10 * potencia(distancia, 3) + 5.38877864386667E-07 * potencia(distancia, 2) + 0.00128187221557141 * distancia + 0.154906920623034;
                } else {
                    return -7.136015321E-21 * potencia(distancia, 6) + 4.98159298960106E-16 * potencia(distancia, 5) - 1.44409539053364E-11 * potencia(distancia, 4)
                            + 2.22454889567679E-07 * potencia(distancia, 3) - 0.00192038348567183 * potencia(distancia, 2) + 8.80715341577015 * distancia - 16678.5831348451;
                }

            case 2:
                if (isRasante()) {
                    return 1.7124578E-23 * potencia(distancia, 6) - 7.81746973355E-19 * potencia(distancia, 5) + 1.33982720041196E-14 * potencia(distancia, 4)
                            - 1.04955555080684E-10 * potencia(distancia, 3) + 4.61840202428676E-07 * potencia(distancia, 2) + 0.000971281970748805 * distancia + 0.22408856000402;
                } else {
                    return -4.254635016E-21 * potencia(distancia, 6) + 3.59115139005049E-16 * potencia(distancia, 5) - 1.2587277345123E-11 * potencia(distancia, 4)
                            + 2.34468920984924E-07 * potencia(distancia, 3) - 0.00244783308960135 * potencia(distancia, 2) + 13.578328693543 * distancia - 31170.0959682212;
                }


            default:
                return 0;
        }
    }

    @Override
    public double getTFi8_Drift(double tiempoVuelo, int carga) {
       if (isLG){
        switch (carga) {
            case 1:
               return 0.000318889798713258*potencia(tiempoVuelo,3 )- 0.0188876353083333*potencia(tiempoVuelo,2 )
                       + 0.618339262328571*potencia(tiempoVuelo, 1)- 1.87457323214531;

            case 2:
                return -4.83202959320806E-09*potencia(tiempoVuelo,6 )+ 1.25847075489516E-06*potencia(tiempoVuelo,5 )- 0.000119970842657553*potencia(tiempoVuelo,4 )
                        + 0.00523343221593131*potencia(tiempoVuelo,3 )- 0.100512362152128*potencia(tiempoVuelo,2 )+ 0.95396461446425*potencia(tiempoVuelo,1 )- 1.09227555642178;
            default:
                return 0;
        }
       }
       else {///Si es L119
           switch (carga) {
               case 1:
                   return -7.28546146257529E-09*potencia(tiempoVuelo,6 )+ 1.77626039725467E-06*potencia(tiempoVuelo,5 )- 0.0001562050614718*potencia(tiempoVuelo,4 )
                           + 0.00629578052191492*potencia(tiempoVuelo,3 )- 0.110847533688806*potencia(tiempoVuelo,2 )+ 0.977130896532179*potencia(tiempoVuelo,1 )- 0.960605064456982;

               case 2:
                   return -5.27744387684363E-09*potencia(tiempoVuelo,6 )+ 1.37467423166356E-06*potencia(tiempoVuelo,5 )- 0.000130979089990207*potencia(tiempoVuelo,4 )
                           + 0.00570555762262163*potencia(tiempoVuelo,3 )- 0.109174698364108*potencia(tiempoVuelo,2 )+ 1.0374898249278*potencia(tiempoVuelo,1 )- 1.2088517222086;
               default:
                   return 0;
           }
       }
    }

    @Override
    public double getTFi9_CrossWind(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 1.20303E-25 * potencia(distancia, 6)
                            - 1.753556347E-21 * potencia(distancia, 5)
                            - 1.8819209316025E-17 * potencia(distancia, 4)
                            + 2.86231079037012E-13 * potencia(distancia, 3)
                            + 2.98316429935344E-10 * potencia(distancia, 2)
                            + 0.000039035374120644 * distancia + 0.0000557602626258813;
                } else {
                    return -2.5360336E-23 * potencia(distancia, 6)
                            + 1.653344079223E-18 * potencia(distancia, 5)
                            - 4.46321108912097E-14 * potencia(distancia, 4)
                            + 6.37472186076218E-10 * potencia(distancia, 3)
                            - 5.06206698993142E-06 * potencia(distancia, 2)
                            + 0.0209335641220115 * distancia
                            - 32.9117752173483;
                }

            case 2:
                if (isRasante()) {
                    return 1.33187E-25 * potencia(distancia, 6)
                            - 5.316317536E-21 * potencia(distancia, 5)
                            + 7.348371079791E-17 * potencia(distancia, 4)
                            - 4.82247325681111E-13 * potencia(distancia, 3)
                            + 2.82167833412852E-09 * potencia(distancia, 2)
                            + 0.0000271601155041878 * distancia
                            + 0.000966014318919406;
                } else {
                    return -4.1857888E-23 * potencia(distancia, 6)
                            + 3.529522260195E-18 * potencia(distancia, 5)
                            - 1.23538281736349E-13 * potencia(distancia, 4)
                            + 2.29650481123409E-09 * potencia(distancia, 3)
                            - 0.0000238987724473375 * potencia(distancia, 2)
                            + 0.13179805398033 * distancia
                            - 298.644779397765;
                }
            default:
                return 0;
        }
    }

    @Override

    public double getTFii10_muzzleVelDec(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 6.84427141809796E-16 * potencia(distancia, 4)
                            - 1.15862210503739E-11 * potencia(distancia, 3)
                            - 9.4032233627948E-08 * potencia(distancia, 2)
                            + 0.00371895484226989 * distancia
                            + 0.101945212850608;
                } else {
                    return -3.53850320784E-18 * potencia(distancia, 5)
                            + 1.96873102680202E-13 * potencia(distancia, 4)
                            - 4.36445855575041E-09 * potencia(distancia, 3)
                            + 0.0000481760489323109 * potencia(distancia, 2)
                            - 0.262758547473414 * distancia
                            + 581.555661603899;
                }

            case 2:
                if (isRasante()) {
                    return 2.513711E-24 * potencia(distancia, 6)
                            - 1.05679542341E-19 * potencia(distancia, 5)
                            + 1.74503650577377E-15 * potencia(distancia, 4)
                            - 1.15688897365715E-11 * potencia(distancia, 3)
                            - 8.66402807137012E-08 * potencia(distancia, 2)
                            + 0.00325599451863923 * distancia + 0.0155498191161314;
                } else {
                    return -2.45144875E-22 * potencia(distancia, 6)
                            + 1.6972250523643E-17 * potencia(distancia, 5)
                            - 4.67343527023106E-13 * potencia(distancia, 4)
                            + 6.39391989354365E-09 * potencia(distancia, 3)
                            - 0.0000434635567216901 * potencia(distancia, 2)
                            + 0.119475772604346 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getTFii11_muzzleVelInc(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -1.6537311E-23 * potencia(distancia, 6)
                            + 6.4691540136E-19 * potencia(distancia, 5)
                            - 9.84981086091667E-15 * potencia(distancia, 4)
                            + 7.03925195019743E-11 * potencia(distancia, 3)
                            - 9.99402150612603E-08 * potencia(distancia, 2)
                            - 0.0032605080257578 * distancia
                            - 0.120183102989431;
                } else {
                    return 1.622046317E-21 * potencia(distancia, 6)
                            - 1.17759168005427E-16 * potencia(distancia, 5)
                            + 3.53450877518387E-12 * potencia(distancia, 4)
                            - 5.61332725743087E-08 * potencia(distancia, 3)
                            + 0.000497524929419075 * potencia(distancia, 2)
                            - 2.33581126388531 * distancia
                            + 4526.67932494069;
                }

            case 2:
                if (isRasante()) {
                    return -1.948933E-24 * potencia(distancia, 6)
                            + 9.1885990953E-20 * potencia(distancia, 5)
                            - 1.72068012466067E-15 * potencia(distancia, 4)
                            + 1.37917937585442E-11 * potencia(distancia, 3)
                            + 5.12394319751586E-08 * potencia(distancia, 2)
                            - 0.00298436899889509 * distancia - 0.03409561416629;
                } else {
                    return -9.2745144E-23 * potencia(distancia, 6)
                            + 6.594734449911E-18 * potencia(distancia, 5)
                            - 1.8583407856705E-13 * potencia(distancia, 4)
                            + 2.5962348382258E-09 * potencia(distancia, 3)
                            - 0.0000179810288045701 * potencia(distancia, 2)
                            + 0.0474428180605173 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getTFii12_VientoLongitudinal1KNCabeza(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -2.6288637E-23 * potencia(distancia, 6)
                            + 1.017322924762E-18 * potencia(distancia, 5)
                            - 1.52035840431385E-14 * potencia(distancia, 4)
                            + 1.11940386089664E-10 * potencia(distancia, 3)
                            - 3.03230549599721E-07 * potencia(distancia, 2)
                            + 0.000500279578889153 * distancia - 0.173950360034715;
                } else {
                    return 1.034303856E-21 * potencia(distancia, 6)
                            - 7.1409441408024E-17 * potencia(distancia, 5)
                            + 2.03365793134083E-12 * potencia(distancia, 4)
                            - 3.05999018144594E-08 * potencia(distancia, 3)
                            + 0.000256737274350491 * potencia(distancia, 2)
                            - 1.13881861677948 * distancia + 2102.4534083126;
                }

            case 2:
                if (isRasante()) {
                    return -1.5215948E-23 * potencia(distancia, 6)
                            + 7.22372361951E-19 * potencia(distancia, 5)
                            - 1.29775179675633E-14 * potencia(distancia, 4)
                            + 1.11771257762963E-10 * potencia(distancia, 3)
                            - 3.86974478183699E-07 * potencia(distancia, 2)
                            + 0.000697704311861003 * distancia - 0.264793527116126;
                } else {
                    return 2.44809521E-22 * potencia(distancia, 6)
                            - 1.7730489974838E-17 * potencia(distancia, 5)
                            + 5.08933651108658E-13 * potencia(distancia, 4)
                            - 7.23538659541667E-09 * potencia(distancia, 3)
                            + 0.000050855770950875 * potencia(distancia, 2)
                            - 0.13923299126327 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getTFii13_VientoLongitudinal1KNCola(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -4.252518E-24 * potencia(distancia, 6)
                            + 1.91402192335E-19 * potencia(distancia, 5)
                            - 2.89126658260987E-15 * potencia(distancia, 4)
                            + 1.69668844501818E-11 * potencia(distancia, 3)
                            - 1.19165527465096E-07 * potencia(distancia, 2)
                            + 0.0000467401679264761 * distancia - 0.0023834357249035;
                } else {
                    return -1.64786636E-22 * potencia(distancia, 6)
                            + 1.2682564393237E-17 * potencia(distancia, 5)
                            - 4.06944989967233E-13 * potencia(distancia, 4)
                            + 6.96897433709747E-09 * potencia(distancia, 3)
                            - 0.0000671952787363716 * potencia(distancia, 2)
                            + 0.34526275646876 * distancia - 752.258224846585;
                }

            case 2:
                if (isRasante()) {
                    return -5.70454E-25 * potencia(distancia, 6)
                            + 3.1290087247E-20 * potencia(distancia, 5)
                            - 5.32770107706289E-16 * potencia(distancia, 4)
                            + 2.24466200801057E-12 * potencia(distancia, 3)
                            - 5.58877777043221E-08 * potencia(distancia, 2)
                            - 1.34731853052017E-06 * distancia + 0.00749514131894102;
                } else {
                    return -6.6868437E-23 * potencia(distancia, 6)
                            + 5.124440556638E-18 * potencia(distancia, 5)
                            - 1.55830907802145E-13 * potencia(distancia, 4)
                            + 2.34586505776235E-09 * potencia(distancia, 3)
                            - 0.0000173897597051109 * potencia(distancia, 2)
                            + 0.0488585848361254 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getFii14_TempAireDec(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 2.1020475E-23 * potencia(distancia, 6)
                            - 1.448004921005E-18 * potencia(distancia, 5)
                            + 3.00761698210065E-14 * potencia(distancia, 4)
                            - 2.36245100117817E-10 * potencia(distancia, 3)
                            + 7.76940190908915E-07 * potencia(distancia, 2)
                            - 0.00141338198555643 * distancia + 0.686874744871128;

                } else {
                    return -1.150398171E-21 * potencia(distancia, 6)
                            + 8.3828020931148E-17 * potencia(distancia, 5)
                            - 2.53647609874541E-12 * potencia(distancia, 4)
                            + 4.07961876742817E-08 * potencia(distancia, 3)
                            - 0.000367766564835824 * potencia(distancia, 2)
                            + 1.76231873598193 * distancia - 3498.92847898027;
                }

            case 2:
                if (isRasante()) {
                    return -1.3739496E-23 * potencia(distancia, 6)
                            + 4.36790405953E-19 * potencia(distancia, 5)
                            - 3.47633315538308E-15 * potencia(distancia, 4)
                            + 9.5754961178959E-12 * potencia(distancia, 3)
                            - 9.70955637974447E-08 * potencia(distancia, 2)
                            - 0.0000161843451564891 * distancia + 0.0320019464234065;
                } else {
                    return 4.08669237E-22 * potencia(distancia, 6)
                            - 2.8933058635599E-17 * potencia(distancia, 5)
                            + 8.16937033157935E-13 * potencia(distancia, 4)
                            - 1.14921683280755E-08 * potencia(distancia, 3)
                            + 0.0000805281116527112 * potencia(distancia, 2)
                            - 0.224305223207921 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getFii15_TempAireInc(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -3.4981515E-23 * potencia(distancia, 6)
                            + 1.692341463137E-18 * potencia(distancia, 5)
                            - 2.74356243362179E-14 * potencia(distancia, 4)
                            + 1.66234681954765E-10 * potencia(distancia, 3)
                            - 3.91814058900986E-07 * potencia(distancia, 2)
                            + 0.00054153821066336 * distancia
                            - 0.173002445767971;

                } else {
                    return 2.865296769E-21 * potencia(distancia, 6)
                            - 2.0901724629499E-16 * potencia(distancia, 5)
                            + 6.30377509335805E-12 * potencia(distancia, 4)
                            - 1.00625292548857E-07 * potencia(distancia, 3)
                            + 0.000896827294501998 * potencia(distancia, 2)
                            - 4.23371313108213 * distancia + 8265.75087146367;
                }

            case 2:
                if (isRasante()) {
                    return -7.357257E-24 * potencia(distancia, 6)
                            + 5.57450420096E-19 * potencia(distancia, 5)
                            - 1.32183171373939E-14 * potencia(distancia, 4)
                            + 1.13528963030038E-10 * potencia(distancia, 3)
                            - 3.29982399367018E-07 * potencia(distancia, 2)
                            + 0.00064651780601821 * distancia - 0.232984064696211;
                } else {
                    return -8.6944452E-23 * potencia(distancia, 6)
                            + 6.126985475305E-18 * potencia(distancia, 5)
                            - 1.71661574115568E-13 * potencia(distancia, 4)
                            + 2.38836511678997E-09 * potencia(distancia, 3)
                            - 0.0000165150963766791 * potencia(distancia, 2)
                            + 0.0447951965034008 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getFii16_DensAireDec(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 2.3523637E-23 * potencia(distancia, 6)
                            - 1.333662752857E-18 * potencia(distancia, 5)
                            + 2.58067763209627E-14 * potencia(distancia, 4)
                            - 1.92165075950059E-10 * potencia(distancia, 3)
                            + 1.95268669666215E-07 * potencia(distancia, 2)
                            - 0.001046413392148 * distancia + 0.452643507655406;

                } else {
                    return 6.021343386E-21 * potencia(distancia, 6)
                            - 4.3989176247406E-16 * potencia(distancia, 5)
                            + 1.33236817568029E-11 * potencia(distancia, 4)
                            - 2.14145241503177E-07 * potencia(distancia, 3)
                            + 0.00192627670190201 * potencia(distancia, 2)
                            - 9.19935571345599 * distancia + 18199.1960151203;
                }

            case 2:
                if (isRasante()) {
                    return -4.287078E-24 * potencia(distancia, 6)
                            + 4.7066436753E-20 * potencia(distancia, 5)
                            + 2.53935880613303E-15 * potencia(distancia, 4)
                            - 3.37721046806782E-11 * potencia(distancia, 3)
                            - 2.69126562035815E-07 * potencia(distancia, 2)
                            - 0.000395276754716178 * distancia + 0.16547905599873;
                } else {
                    return -2.11941656E-22 * potencia(distancia, 6)
                            + 1.5840884270389E-17 * potencia(distancia, 5)
                            - 4.69298699293935E-13 * potencia(distancia, 4)
                            + 6.89334678227738E-09 * potencia(distancia, 3)
                            - 0.0000502269467688166 * potencia(distancia, 2)
                            + 0.140143327414989 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getFii17_DensAireInc(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -8.9328272E-23 * potencia(distancia, 6)
                            + 4.068421202085E-18 * potencia(distancia, 5)
                            - 6.72090092034493E-14 * potencia(distancia, 4)
                            + 4.7411533037565E-10 * potencia(distancia, 3)
                            - 1.08258233015968E-06 * potencia(distancia, 2)
                            + 0.00227791053439371 * distancia - 0.978766298272329;

                } else {
                    return -4.270066295E-21 * potencia(distancia, 6)
                            + 2.97003390673871E-16 * potencia(distancia, 5)
                            - 8.59910605353463E-12 * potencia(distancia, 4)
                            + 1.32605790707648E-07 * potencia(distancia, 3)
                            - 0.00114828689123957 * potencia(distancia, 2)
                            + 5.29632179443746 * distancia - 10137.4211798113;
                }

            case 2:
                if (isRasante()) {
                    return -4.3845269E-23 * potencia(distancia, 6)
                            + 2.378660636993E-18 * potencia(distancia, 5)
                            - 4.74708089329202E-14 * potencia(distancia, 4)
                            + 4.07540099005299E-10 * potencia(distancia, 3)
                            - 1.11580176076487E-06 * potencia(distancia, 2)
                            + 0.00242994687704368 * distancia - 0.880772933640401;
                } else {
                    return 1.726039636E-21 * potencia(distancia, 6)
                            - 1.27780420223999E-16 * potencia(distancia, 5)
                            + 3.7490048364992E-12 * potencia(distancia, 4)
                            - 5.45218004077697E-08 * potencia(distancia, 3)
                            + 0.000393203570638434 * potencia(distancia, 2)
                            - 1.12033465318381 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getFii18_BoxWeitghDec(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -2.3962307E-23 * potencia(distancia, 6)
                            + 1.288248076507E-18 * potencia(distancia, 5)
                            - 2.46487102754397E-14 * potencia(distancia, 4)
                            + 1.78403104794696E-10 * potencia(distancia, 3)
                            + 2.17392691255822E-07 * potencia(distancia, 2)
                            - 0.00812743875008758 * distancia
                            - 0.401796218380994;

                } else {
                    return 2.25520689E-20 * potencia(distancia, 6)
                            - 1.64970064283807E-15 * potencia(distancia, 5)
                            + 5.00087867074893E-11 * potencia(distancia, 4)
                            - 8.03995341622152E-07 * potencia(distancia, 3)
                            + 0.00722938614445629 * potencia(distancia, 2)
                            - 34.4702180332309 * distancia
                            + 68078.0732744176;
                }

            case 2:
                if (isRasante()) {
                    return -3.48439E-24 * potencia(distancia, 6)
                            + 3.62082114344E-19 * potencia(distancia, 5)
                            - 1.03846240918462E-14 * potencia(distancia, 4)
                            + 9.26783942655899E-11 * potencia(distancia, 3)
                            + 3.94055548795513E-07 * potencia(distancia, 2)
                            - 0.00789078496753248 * distancia
                            - 0.193325262776852;

                } else {
                    return -2.97842903E-22 * potencia(distancia, 6)
                            + 2.138713770118E-17 * potencia(distancia, 5)
                            - 6.10331174492744E-13 * potencia(distancia, 4)
                            + 8.64816439207633E-09 * potencia(distancia, 3)
                            - 0.0000607763090751945 * potencia(distancia, 2)
                            + 0.169111220566265 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getFii19_BoxWeitghInc(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return 2.5484117E-23 * potencia(distancia, 6)
                            - 1.293824373376E-18 * potencia(distancia, 5)
                            + 2.32464372987267E-14 * potencia(distancia, 4)
                            - 1.5009733884795E-10 * potencia(distancia, 3)
                            - 4.18283884239118E-07 * potencia(distancia, 2)
                            + 0.00865614319402127 * distancia + 0.269638530202932;

                } else {
                    return 1.2666427316E-20 * potencia(distancia, 6)
                            - 8.78313476483772E-16 * potencia(distancia, 5)
                            + 2.52442967365228E-11 * potencia(distancia, 4)
                            - 3.85080384869966E-07 * potencia(distancia, 3)
                            + 0.00328927833729719 * potencia(distancia, 2)
                            - 14.922315351193 * distancia
                            + 28108.7221303763;
                }

            case 2:
                if (isRasante()) {
                    return 2.396485E-24 * potencia(distancia, 6)
                            - 2.94073509966E-19 * potencia(distancia, 5)
                            + 8.75632723137176E-15 * potencia(distancia, 4)
                            - 7.42018658033222E-11 * potencia(distancia, 3)
                            - 4.93498036865936E-07 * potencia(distancia, 2)
                            + 0.00812715263538877 * distancia
                            + 0.143063108855131;

                } else {
                    return 5.75570246E-22 * potencia(distancia, 6)
                            - 4.2358132477701E-17 * potencia(distancia, 5)
                            + 1.23985455165628E-12 * potencia(distancia, 4)
                            - 1.80407313205098E-08 * potencia(distancia, 3)
                            + 0.000130439185520359 * potencia(distancia, 2)
                            - 0.37457635834653 * distancia;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getGOrdenadaMax_(double elevacion, int carga) {
        switch (carga) {
            case 1:
                return 2.52398065993219E-15 * potencia(elevacion, 6) - 1.09563769562753E-11 * potencia(elevacion, 5) + 1.75928806123854E-08 * potencia(elevacion, 4)
                        - 0.0000167510264716952 * potencia(elevacion, 3) + 0.0137082948608622 * potencia(elevacion, 2) + 0.0293952400097623 * elevacion - 0.078505608253181;
            case 2:
                return 3.10835732931725E-15 * potencia(elevacion, 6) - 1.41885161168779E-11 * potencia(elevacion, 5) + 2.40524470652981E-08 * potencia(elevacion, 4)
                        - 0.0000233228305166477 * potencia(elevacion, 3) + 0.0178883180333287 * potencia(elevacion, 2) + 0.093044305278454 * elevacion - 0.97401447687298;
            default:
                return 0;
        }
    }

    @Override
    public double getTG_ErrorProb_Alcance(double tiempoV, int carga) {
        if (carga==1)return 4.19988359953542E-10*potencia(tiempoV,6 )- 9.70475864607578E-08*potencia(tiempoV,5 )+ 0.0000097352003286233*potencia(tiempoV,4 )- 0.000526847777552158*potencia(tiempoV,3 )+ 0.0101248160591605*potencia(tiempoV,2 )+ 0.421208786699744*potencia(tiempoV,1 )+ 16.1256092073301;
        else if (carga==2)return 1.4846076920442E-10*potencia(tiempoV,6 )- 4.30779204744179E-08*potencia(tiempoV,5 )+ 5.92673655813968E-06*potencia(tiempoV,4 )- 0.000436168530156131*potencia(tiempoV,3 )+ 0.011672545642036*potencia(tiempoV,2 )+ 0.326722291620461*potencia(tiempoV,1 )+ 17.4108346417017;
        else return 0;
    }

    @Override
    public double getTG_ErrorProb_Azimut(double tiempoV, int carga) {

        if (carga==1)return -7.30720276211831E-10*potencia(tiempoV,6 )+ 1.97214806570336E-07*potencia(tiempoV,5 )- 0.0000203420212266013*potencia(tiempoV,4 )+ 0.00101313952965207*potencia(tiempoV,3 )- 0.0247047154483727*potencia(tiempoV,2 )+ 0.429893666918916*potencia(tiempoV,1 )- 0.651652986903461;
        else if (carga==2)return -2.98119641948776E-10*potencia(tiempoV,6 )+ 9.33357883455563E-08*potencia(tiempoV,5 )- 0.0000111074425641588*potencia(tiempoV,4 )+ 0.000639308281654165*potencia(tiempoV,3 )- 0.0180750978422521*potencia(tiempoV,2 )+ 0.374543916980483*potencia(tiempoV,1 )- 0.370505336195448;
        else return 0;
    }

    @Override
    public double getH_DistanciaPorRotacionTierra(double distancia, double azimut, double latitud, int carga) {
        double azimutGrados = convierteMilesimas_a_Grados(azimut);
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return (3.7750239955949E-15 * potencia(distancia, 4)
                            - 1.19609557108895E-10 * potencia(distancia, 3)
                            + 1.45784056628935E-06 * potencia(distancia, 2)
                            - 0.0103987948325858 * distancia + 1.98851148844451) * Math.sin(Math.toRadians(azimutGrados))
                            * (-0.0001 * potencia(latitud, 2) - 0.0012 * latitud + 1.0086);

                } else {
                    return (-2.08333333335103E-13 * potencia(distancia, 4)
                            + 9.91666666675195E-09 * potencia(distancia, 3)
                            - 0.0001767916666682 * potencia(distancia, 2)
                            + 1.39008333334552 * distancia - 4025.00000003618) * Math.sin(Math.toRadians(azimutGrados))
                            * (-0.0001 * potencia(latitud, 2) - 0.0012 * latitud + 1.0086);
                }

            case 2:
                if (isRasante()) {
                    return (2.30213542906178E-15 * potencia(distancia, 4)
                            - 9.58627715591485E-11 * potencia(distancia, 3)
                            + 0.0000015065293323785 * potencia(distancia, 2)
                            - 0.0122618215977409 * distancia + 2.4298642535554) * Math.sin(Math.toRadians(azimutGrados))
                            * (-0.0001 * potencia(latitud, 2) - 0.0012 * latitud + 1.0086);

                } else {
                    return (-1.25000000000969E-13 * potencia(distancia, 4)
                            + 7.25000000005753E-09 * potencia(distancia, 3)
                            - 0.000157875000001283 * potencia(distancia, 2)
                            + 1.51975000001274 * distancia - 5402.00000004755) * Math.sin(Math.toRadians(azimutGrados))
                            * (-0.0001 * potencia(latitud, 2) - 0.0012 * latitud + 1.0086);
                }
            default:
                return 0;
        }
    }


    public static void main(String[] args) {

        MunicionER50 nuevo = new MunicionER50();
    }


}
