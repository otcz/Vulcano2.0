package com.company.balistic;



/***
 *Se puede determinar si la carga puede alcanzar un blanco, consultando el resultado del metodo: isPosibble(,,,) o consultando la variable isPossible
 */


public class AmmunitionM1 extends Tools implements Municion {
    public int carga;
    public int cuadros;
    boolean rasante = true;
    public boolean isPossible;

    public boolean isLG = true;


    public AmmunitionM1() {
        setRasante(true);
        setCuadros(3);
        setCarga(1);
        double elevacion = 300;
        double distancia = 3000;
        double azimut = 900;
        double intervalo = 100;
        double intervaloDMP_Arma = -500;

        isPossible(distancia, intervalo, 2);

        System.out.println("isPossible = " + isPossible);
        System.out.println("getCarga() = " + getCarga());
        System.out.println("elevacion = " + elevacion);
        System.out.println("distancia = " + distancia);
        System.out.println("azimut = " + azimut);
        System.out.println("intervalo = " + intervalo);
        System.out.println("intervaloDMP_Arma = " + intervaloDMP_Arma);
        System.out.println(" ");
        System.out.println("Carga automatica: "+seleccionCargaAutomatica(distancia,intervalo));

        System.out.println("getTA_LineaMeteorologica(elevacion) = " + getTA_LineaMeteorologica(elevacion,getCarga()));
        System.out.println("getTBDistanciaPorComplementario(distancia,) = " + getTBDistanciaPorComplementario(distancia,intervalo,getCarga()));
        System.out.println("getC() = " + getC(elevacion));
        System.out.println("getTipoMunicion() = " + getTipoMunicion());
        System.out.println("getCuadros() = " + getCuadros());
        System.out.println("getCuadrosStandard() = " + getCuadrosStandard());

        System.out.println("getTG_AnguloComp_Mas(distancia) = " + getTG_AnguloComp_Mas(distancia,getCarga()));
        System.out.println("getTG_AnguloComp_Menos(distancia) = " + getTG_AnguloComp_Menos(distancia,getCarga()));
        System.out.println("getTC_ComponenteVientoCabeza() = " + getTC_ComponenteVientoCabeza(azimut));
        System.out.println("getTC_ComponenteVientoCruzado(azimut) = " + getTC_ComponenteVientoCruzado(azimut));
        System.out.println("getTD_CorreccionDensidad(70) = " + getTD_CorreccionDensidad(intervaloDMP_Arma));
        System.out.println("getTD_CorreccionTemperatura(70) = " + getTD_CorreccionTemperatura(intervaloDMP_Arma));
        System.out.println("getTE_Propellanttemperature(22) = " + getTE_Propellanttemperature(22,getCarga()));
        System.out.println("getTFi1_DistanciaApuntada(elevacion) = " + getTFi1_DistanciaApuntada(elevacion,getCarga()));
        System.out.println("getTFi2_Elevacion(distancia) = " + getTFi2_Elevacion(distancia,getCarga()));
        System.out.println("getTFi4_HeighExplosure10m(distancia) = " + getTFi4_HeighExplosure10m(distancia,getCarga()));
        System.out.println("getTFi5_ChangeRangeFor1Mil(distancia) = " + getTFi5_ChangeRangeFor1Mil(distancia,getCarga()));
        System.out.println("getTFi6_Fork(distancia) = " + getTFi6_Fork(distancia,getCarga()));
        System.out.println("getTFi7_TiempoVuelo(distancia) = " + getTFi7_TiempoVuelo(distancia,getCarga()));
        System.out.println("getTFi8_Drift(distancia) = " + getTFi8_Drift(distancia,getCarga()));
        System.out.println("getTFi9_CrossWind(distancia) = " + getTFi9_CrossWind(distancia,getCarga()));
        System.out.println("getTFii10_muzzleVelDec(distancia) = " + getTFii10_muzzleVelDec(distancia,getCarga()));
        System.out.println("getTFii11_muzzleVelInc(distancia) = " + getTFii11_muzzleVelInc(distancia,getCarga()));
        System.out.println("getTFii12_VientoLongitudinal1KNCabeza(distancia) = " + getTFii12_VientoLongitudinal1KNCabeza(distancia,getCarga()));
        System.out.println("getTFii13_VientoLongitudinal1KNCola(distancia) = " + getTFii13_VientoLongitudinal1KNCola(distancia,getCarga()));
        System.out.println("getFii14_TempAireDec(distancia) = " + getFii14_TempAireDec(distancia,getCarga()));
        System.out.println("getFii15_TempAireInc(distancia) = " + getFii15_TempAireInc(distancia,getCarga()));
        System.out.println("getFii16_DensAireDec(distancia) = " + getFii16_DensAireDec(distancia,getCarga()));
        System.out.println("getFii17_DensAireInc(distancia) = " + getFii17_DensAireInc(distancia,getCarga()));
        System.out.println("getFii18_BoxWeitghDec(distancia) = " + getFii18_BoxWeitghDec(distancia,getCarga()));
        System.out.println("getFii19_BoxWeitghInc() = " + getFii19_BoxWeitghInc(distancia,getCarga()));
        System.out.println("getGOrdenadaMax_(elevacion) = " + getGOrdenadaMax_(elevacion,getCarga()));
        System.out.println("getTG_AnguloComp_Menos(distancia) = " + getTG_AnguloComp_Menos(distancia,getCarga()));
        System.out.println("getTG_AnguloComp_Mas(distancia) = " + getTG_AnguloComp_Mas(distancia,getCarga()));
        System.out.println("getH_DistanciaPorRotacionTierra(distancia,azimut,10) = " + getH_DistanciaPorRotacionTierra(distancia, azimut, 10,getCarga()));

    }

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
        this.carga = carga;
    }

    @Override
    public int getCarga() {
        return this.carga;
    }

    @Override
    public int getMuzzleVelocity() {
        if (getCarga() == 1) return 202;
        else if (getCarga() == 2) return 220;
        else if (getCarga() == 3) return 241;
        else if (getCarga() == 4) return 274;
        else if (getCarga() == 5) return 315;
        else if (getCarga() == 6) return 383;
        else if (getCarga() == 7) return 488;
        else return 0;
    }

    @Override
    public void setCuadros(int cuadros) {
        this.cuadros = cuadros;
    }

    @Override
    public int getCuadros() {
        return this.cuadros;
    }

    @Override
    public int getCuadrosStandard() {
        return 3;
    }

    @Override
    public double getTemperaturaPropelenteStandard() {
        return 21;
    }

    @Override
    public String getTipoMunicion() {
        return "105 mm - HE M1 ";
    }

    @Override
    public double getWeitgh() {
        return 14.969;
    }

    @Override
    public double getC(double elevation) {
        switch (getCarga()) {
            case 1:
                return -1.084628548283E-18*potencia(elevation,6 )+ 4.1819969335486E-15*potencia(elevation,5 )
                        - 6.02637982185146E-12*potencia(elevation,4 )+ 4.28644681014357E-09*potencia(elevation,3 )
                        - 1.93669333039274E-06*potencia(elevation,2 )+ 0.000698740205024776*potencia(elevation,1 )- 0.026839381792106;
            case 2:
                return -2.181914859036E-18*potencia(elevation,6 )+ 7.86142197682809E-15*potencia(elevation,5 )
                        - 1.06202728796169E-11*potencia(elevation,4 )+ 6.97083318075907E-09*potencia(elevation,3 )
                        - 2.73341725043043E-06*potencia(elevation,2 )+ 0.000820339938544111*potencia(elevation, 1)- 0.0186731916809279;
            case 3:
                return 2.03079840519E-19*potencia(elevation,6 )- 1.17725798023585E-15*potencia(elevation,5 )
                        + 2.62967928638276E-12*potencia(elevation,4 )- 2.50510937574265E-09*potencia(elevation,3 )
                        + 7.68289388157867E-07*potencia(elevation,2 )+ 0.00012539714117289*potencia(elevation, 1)+ 0.0611437620764428;
            case 4:
                return -3.458070247277E-18*potencia(elevation,6 )+ 1.29969676360762E-14*potencia(elevation,5 )- 1.80575994135044E-11*potencia(elevation,4 )
                        + 1.13796410333123E-08*potencia(elevation,3 )- 3.27214757055439E-06*potencia(elevation,2 )+ 0.000400831397943624*potencia(elevation,1 )
                        + 0.117911495051045;
            case 5:
                return -7.90172761065E-19*potencia(elevation,6 )+ 2.96930595369352E-15*potencia(elevation,5 )
                        - 4.08359066933799E-12*potencia(elevation,4 )+ 2.52173648823839E-09*potencia(elevation,3 )
                        - 6.89221560716247E-07*potencia(elevation,2 )- 0.0000134449412009276*potencia(elevation, 1)+ 0.207636640271104;
            case 6:
                return -2.877218730183E-18*potencia(elevation,6 )+ 1.10379225044574E-14*potencia(elevation,5 )
                        - 1.52162306673066E-11*potencia(elevation,4 )+ 8.24063686706753E-09*potencia(elevation,3 )
                        - 1.29939731625262E-07*potencia(elevation,2 )- 0.00164126066939103*potencia(elevation,1 )+ 0.773455429303346;
            case 7:
                return -6.821664555968E-18*potencia(elevation,6 )+ 2.78943706461315E-14*potencia(elevation,5 )
                        - 4.39137091106085E-11*potencia(elevation,4 )+ 3.28077522451453E-08*potencia(elevation,3 )
                        - 0.0000108774513121366*potencia(elevation,2 )+ 0.000277437308849501*potencia(elevation,1 )
                        + 0.903389174539389;
            default:
                return 0;
        }


    }

    @Override
    public double getDeltaCVelInicial(double elevation, double cambioVel, int carga) {
        switch (getCarga()) {
            case 1:
                return (1.970629633655E-18*potencia(elevation,6 )- 8.43840137845986E-15*potencia(elevation,5 )+ 1.43041080532058E-11*potencia(elevation,4 )
                        - 1.2205539058546E-08*potencia(elevation,3 )+ 5.52324700513274E-06*potencia(elevation,2 )- 0.00128518435671207*potencia(elevation, 1)
                        + 0.13784393243017) * cambioVel;
            case 2:
                return (1.85058132034E-18*potencia(elevation,6 )- 7.75803855677385E-15*potencia(elevation,5 )+ 1.28912223412424E-11*potencia(elevation,4 )
                        - 1.08137597078768E-08*potencia(elevation,3 )+ 4.83303011471269E-06*potencia(elevation,2 )- 0.00111582640236458*potencia(elevation,1 )
                        + 0.118694791785137) * cambioVel;
            case 3:
                return (2.260494692521E-18*potencia(elevation,6 )- 9.78096427862575E-15*potencia(elevation,5 )+ 1.66848679570088E-11*potencia(elevation,4 )
                        - 1.4230560555062E-08*potencia(elevation,3 )+ 6.34859608422116E-06*potencia(elevation,2 )- 0.00140897027334312*potencia(elevation,1 )
                        + 0.132573510080991) * cambioVel;
            case 4:
                return (7.55325263133E-19*potencia(elevation,6 )- 3.30377352580197E-15*potencia(elevation,5 )+ 5.75095041693938E-12*potencia(elevation,4 )
                        - 5.08197316345529E-09*potencia(elevation,3 )+ 0.0000024125627186235*potencia(elevation,2 )- 0.000599401452943168*potencia(elevation,1 )
                        + 0.0702318053902821) * cambioVel;
            case 5:
                return (1.292798024967E-18 * Math.pow(elevation, 6) - 5.33471588915655E-15 * Math.pow(elevation, 5) + 8.65535794871233E-12 * Math.pow(elevation, 4)
                        - 6.99865745805236E-09 * Math.pow(elevation, 3) + 2.95278920898719E-06 * Math.pow(elevation, 2) - 0.000621478662031099 * Math.pow(elevation, 1)
                        + 0.0566235261997952) * cambioVel;
            case 6:
                return (1.31122701389044 * Math.pow(elevation, -1.04638459790926)) * cambioVel;
            case 7:
                return (4.37152415827E-19 * Math.pow(elevation, 6) - 1.88129827807704E-15 * Math.pow(elevation, 5) + 3.19549445828475E-12 * Math.pow(elevation, 4)
                        - 2.7212884575506E-09 * Math.pow(elevation, 3) + 1.22137668645357E-06 * Math.pow(elevation, 2) - 0.000278120822654742 * Math.pow(elevation, 1)
                        + 0.0277490120836652) * cambioVel;
            default:
                return 0;
        }

    }

    @Override
    public double getDeltaCVientoLongitudinal(double elevation, double vientoLong, int carga) {
        switch (getCarga()) {
            case 1:
                return (1.49757190297E-19*potencia(elevation,6 )- 6.15883682268181E-16*potencia(elevation,5 )+ 9.88701978780902E-13*potencia(elevation,4 )
                        - 7.85160312693668E-10*potencia(elevation,3 )+ 3.20878207742238E-07*potencia(elevation,2 )- 0.0000621897306400972*potencia(elevation, 1)
                        + 0.00468514201238725) * vientoLong;
            case 2:
                return (-7.8028194746E-20*potencia(elevation,6 )+ 2.61015342947242E-16*potencia(elevation,5 )- 3.16957335461643E-13*potencia(elevation,4 )
                        + 1.63709057248726E-10*potencia(elevation,3 )- 2.70963231807534E-08*potencia(elevation,2 )- 3.86656581853656E-06*potencia(elevation,1 )
                        + 0.00175579645686226) * vientoLong;
            case 3:
                return (4.5352203266E-20*potencia(elevation,6 )- 2.0980769893563E-16*potencia(elevation,5 )+ 3.70066123952247E-13*potencia(elevation,4 )
                        - 3.10533868230006E-10*potencia(elevation,3 )+ 1.2492990376579E-07*potencia(elevation,2 )- 0.0000210692669443489*potencia(elevation, 1)
                        + 0.00158822159275721) * vientoLong;
            case 4:
                return (-2.03881132633E-19*potencia(elevation,6 )+ 8.02554742610699E-16*potencia(elevation,5 )- 1.24229596203611E-12*potencia(elevation,4 )
                        + 9.57590795654032E-10*potencia(elevation,3 )- 3.8291346395913E-07*potencia(elevation,2 )+ 0.0000744192568759641*potencia(elevation,1 )
                        - 0.00502747904328947) * vientoLong;
            case 5:
                return (-4.1929408336E-20 * Math.pow(elevation, 6) + 1.63184113600654E-16 * Math.pow(elevation, 5) - 2.43612914004217E-13 * Math.pow(elevation, 4)
                        + 1.72728075596867E-10 * Math.pow(elevation, 3) - 5.65595170433976E-08 * Math.pow(elevation, 2) + 5.34798387552094E-06 * Math.pow(elevation, 1)
                        + 0.00127091618389142) * vientoLong;
            case 6:
                return (-6.097712746027E-18 * Math.pow(elevation, 5) + 2.2745387575863E-14 * Math.pow(elevation, 4) - 3.33089746327303E-11 * Math.pow(elevation, 3)
                        + 2.57413462396763E-08 * Math.pow(elevation, 2) - 0.000012514100575398 * Math.pow(elevation, 1) + 0.00421386639184775) * vientoLong;
            case 7:
                return (-8.770336667E-21 * Math.pow(elevation, 6) + 3.8092419559122E-17 * Math.pow(elevation, 5) - 6.77345426666045E-14 * Math.pow(elevation, 4)
                        + 6.27046357181204E-11 * Math.pow(elevation, 3) - 3.03887367630129E-08 * Math.pow(elevation, 2) + 5.46270290737283E-06 * Math.pow(elevation, 1)
                        + 0.00150300913724743) * vientoLong;
            default:
                return 0;
        }
    }

    @Override
    public double getDeltaCTemperatura(double elevation, double porcTemp, int carga) {
        switch (getCarga()) {
            case 1:
                return (3.146062245E-21*potencia(elevation,6 )- 2.7723647789087E-17*potencia(elevation,5 )+ 6.65343991603722E-14*potencia(elevation,4 )
                        - 6.28033711128109E-11*potencia(elevation,3 )+ 2.11804226906254E-08*potencia(elevation,2 )+ 3.14608235974491E-07*potencia(elevation,1 )
                        - 0.000888496693709801) * porcTemp;
            case 2:
                return (-1.408425136E-21*potencia(elevation,6 )- 2.4382479893868E-17*potencia(elevation,5 )+ 9.73732661940048E-14*potencia(elevation,4 )
                        - 1.32462190499983E-10*potencia(elevation,3 )+ 8.08434549889198E-08*potencia(elevation,2 )- 0.0000226817667403834*potencia(elevation,1 )
                        + 0.00250370845928113) * porcTemp;
            case 3:
                return (-5.7001141476E-20*potencia(elevation,6 )+ 2.36095693219733E-16*potencia(elevation,5 )- 3.78658382742768E-13*potencia(elevation,4 )
                        + 2.97194770588804E-10*potencia(elevation,3 )- 1.18930433970408E-07*potencia(elevation,2 )+ 0.0000223342848887842*potencia(elevation,1 )
                        - 0.00127979322660289) * porcTemp;
            case 4:
                return (3.689544948E-21*potencia(elevation,6 )- 3.985601707126E-18*potencia(elevation,5 )- 9.71828151994031E-15*potencia(elevation,4 )
                        + 1.76641900966452E-11*potencia(elevation,3 )- 1.05120929467134E-08*potencia(elevation,2 )+ 3.74188133333014E-06*potencia(elevation,1 )
                        - 0.00112626407602743) * porcTemp;
            case 5:
                return (-1.42028318501E-19 * Math.pow(elevation, 6) + 5.69865841138039E-16 * Math.pow(elevation, 5) - 8.80072818612383E-13 * Math.pow(elevation, 4)
                        + 6.47845026672138E-10 * Math.pow(elevation, 3) - 2.22331137285973E-07 * Math.pow(elevation, 2) + 0.0000245975388638535 * Math.pow(elevation, 1)
                        + 0.00215489220643841) * porcTemp;
            case 6:
                return (6.3839337637641E-17 * Math.pow(elevation, 5) - 2.24832216047877E-13 * Math.pow(elevation, 4) + 2.87632934901776E-10 * Math.pow(elevation, 3)
                        - 1.55120142518178E-07 * Math.pow(elevation, 2) + 0.0000245932759207896 * Math.pow(elevation, 1) + 0.00552914992118915) * porcTemp;
            case 7:
                return (1.4798731143E-20 * Math.pow(elevation, 6) - 3.860666661026E-18 * Math.pow(elevation, 5) - 1.29838822731141E-13 * Math.pow(elevation, 4)
                        + 2.6810798401418E-10 * Math.pow(elevation, 3) - 2.13205710233818E-07 * Math.pow(elevation, 2) + 0.000070582902937201 * Math.pow(elevation, 1)
                        - 0.00529646034469691) * porcTemp;
            default:
                return 0;
        }
    }

    @Override
    public double getDeltaCDensidad(double elevation, double porcDens, int carga) {
        switch (getCarga()) {
            case 1:
                return (3.9684113384E-20*potencia(elevation,6 )- 1.63866371537686E-16*potencia(elevation,5 )+ 2.64435483964509E-13*potencia(elevation,4 )
                        - 2.11786879690272E-10*potencia(elevation,3 )+ 8.96632067022493E-08*potencia(elevation,2 )- 0.0000198277768307503*potencia(elevation, 1)
                        + 0.000788325914333758) * porcDens;
            case 2:
                return (-2.3182410461E-19*potencia(elevation,6 )+ 9.28893805089779E-16*potencia(elevation,5 )- 1.44384469595843E-12*potencia(elevation,4 )
                        + 1.0900824659233E-09*potencia(elevation,3 )- 4.05890374422758E-07*potencia(elevation,2 )+ 0.0000657098006447238*potencia(elevation,1 )
                        - 0.00424696007848556) * porcDens;
            case 3:
                return (1.54937891866E-19*potencia(elevation,6 )- 6.45828140234073E-16*potencia(elevation,5 )+ 1.05012581238283E-12*potencia(elevation,4 )
                        - 8.41336052774025E-10*potencia(elevation,3 )+ 3.44962320994476E-07*potencia(elevation,2 )- 0.000067142887569262*potencia(elevation,1 )
                        + 0.00337264528435815) * porcDens;
            case 4:
                return (-1.43832686906E-19*potencia(elevation,6 )+ 6.49907800040115E-16*potencia(elevation,5 )- 1.15903154415436E-12*potencia(elevation,4 )
                        + 1.02861222551367E-09*potencia(elevation,3 )- 4.69470247201188E-07*potencia(elevation,2 )+ 0.000102366511009546*potencia(elevation, 1)
                        - 0.00964357499288695) * porcDens;
            case 5:
                return (-8.3751868029E-20 * Math.pow(elevation, 6) + 3.36152971155007E-16 * Math.pow(elevation, 5) - 5.2794061918079E-13 * Math.pow(elevation, 4)
                        + 4.09786553653232E-10 * Math.pow(elevation, 3) - 1.62970491535462E-07 * Math.pow(elevation, 2) + 0.0000315812546400288 * Math.pow(elevation, 1)
                        - 0.00413940726183779) * porcDens;
            case 6:
                return (2.2225834158554E-17 * Math.pow(elevation, 5) - 9.02588383467402E-14 * Math.pow(elevation, 4) + 1.39228042647831E-10 * Math.pow(elevation, 3)
                        - 1.01472732647443E-07 * Math.pow(elevation, 2) + 0.0000359535572105301 * Math.pow(elevation, 1) - 0.00716966856139587) * porcDens;
            case 7:
                return (-4.3748814775E-20 * Math.pow(elevation, 6) + 2.14761310083183E-16 * Math.pow(elevation, 5) - 4.31870310383392E-13 * Math.pow(elevation, 4)
                        + 4.53463365389385E-10 * Math.pow(elevation, 3) - 2.61961558420299E-07 * Math.pow(elevation, 2) + 0.0000807836006040372 * Math.pow(elevation, 1)
                        - 0.0133291359982346) * porcDens;
            default:
                return 0;
        }
    }

    @Override
    public double getDeltaCCuadros(double elevation, double inc_O_Dec, int carga) {
        switch (getCarga()) {
            case 1:
                return (-4.39048619805E-18*potencia(elevation,6 )+ 1.86950443091812E-14*potencia(elevation,5 )- 3.14506305535682E-11*potencia(elevation,4 )
                        + 2.65611840419444E-08*potencia(elevation,3 )- 0.0000118472101334586*potencia(elevation,2 )+ 0.00269661039987996*potencia(elevation,1 )
                        - 0.275632725658047) * inc_O_Dec;
            case 2:
                return (-4.470191844313E-18*potencia(elevation,6 )+ 1.85994507276838E-14*potencia(elevation,5 )- 3.05711691705114E-11*potencia(elevation,4 )
                        + 2.52463673550719E-08*potencia(elevation,3 )- 0.0000110366566262602*potencia(elevation,2 )+ 0.00247204189281489*potencia(elevation,1 )
                        - 0.25041955080359) * inc_O_Dec;
            case 3:
                return (-8.46452811919E-19*potencia(elevation,6 )+ 3.87375890073136E-15*potencia(elevation,5 )- 7.12461915257315E-12*potencia(elevation,4 )
                        + 6.75868837959017E-09*potencia(elevation,3 )- 3.53302450774909E-06*potencia(elevation,2 )+ 0.00100299207441179*potencia(elevation, 1)
                        - 0.1391138893844) * inc_O_Dec;
            case 4:
                return (-2.151504476257E-18*potencia(elevation,6 )+ 9.19421606593717E-15*potencia(elevation,5 )- 1.55166647624077E-11*potencia(elevation,4 )
                        + 1.31534134129443E-08*potencia(elevation,3 )- 5.90641438477593E-06*potencia(elevation,2 )+ 0.00136551585175719*potencia(elevation,1 )
                        - 0.144777428325215) * inc_O_Dec;
            case 5:
                return (-2.098578158181E-18 * Math.pow(elevation, 6) + 9.06865757001203E-15 * Math.pow(elevation, 5) - 1.53814069649986E-11 * Math.pow(elevation, 4)
                        + 1.29784619374799E-08 * Math.pow(elevation, 3) - 5.70449484202383E-06 * Math.pow(elevation, 2) + 0.00124930436255193 * Math.pow(elevation, 1)
                        - 0.116252547611828) * inc_O_Dec;
            case 6:
                return (2.43572489511565E-16 * Math.pow(elevation, 5) - 9.18746783726269E-13 * Math.pow(elevation, 4) + 1.32081572243935E-09 * Math.pow(elevation, 3)
                        - 9.01694880783108E-07 * Math.pow(elevation, 2) + 0.000293797580623646 * Math.pow(elevation, 1) - 0.0371071267970101) * inc_O_Dec;
            case 7:
                return (-9.09328891693E-19 * Math.pow(elevation, 6) + 3.89396371079083E-15 * Math.pow(elevation, 5) - 6.55997585563354E-12 * Math.pow(elevation, 4)
                        + 5.51335940036407E-09 * Math.pow(elevation, 3) - 2.42141270398963E-06 * Math.pow(elevation, 2) + 0.000529591320488429 * Math.pow(elevation, 1)
                        - 0.0455131980059202) * inc_O_Dec;
            default:
                return 0;
        }
    }

    @Override
    public int seleccionCargaAutomatica(double distancia, double intervalo) {
        //No seteo la carga ya que esta debe ser tomada de manera automatica
        int carga = 1;
        for (carga = 1; carga < 7; carga++) {
            if (isPossible(distancia, intervalo, carga)) {

                return carga;
            }

        }
        return carga;
    }

    @Override
    public boolean isPossible(double distancia, double intervalo, int carga) {
        double anguloSituacion = calculaAnguloCatetosMilesimas(intervalo, distancia);
        double distanciaPorAnguloSit = anguloSituacion * getTFi5_ChangeRangeFor1Mil(distancia, carga);
        double distanciaCompleta = distancia + getTBDistanciaPorComplementario(distancia, intervalo, carga) + distanciaPorAnguloSit + 500;
        double elevacionRequerida = getTFi2_Elevacion(distanciaCompleta, carga);
        if (rasante == true) {
            if (elevacionRequerida > 800) {
                isPossible = false;
            } else if (elevacionRequerida <= 800 && elevacionRequerida > 80) {
                isPossible = true;
            }
        } else if (rasante == false) {
            if (elevacionRequerida < 800) {
                isPossible = false;
            } else if (elevacionRequerida >= 800 && elevacionRequerida < 1200) {
                isPossible = true;
            }
        }
        return isPossible;
    }

    @Override
    public int getTA_LineaMeteorologica(double elevacion, int carga) {
        int lineaMet = 0;
        switch (carga) {
            case 1:

                if (isRank(elevacion, 255.6, -100)) lineaMet = 0;
                else if (isRank(elevacion, 439.1, 255.7)) lineaMet = 1;
                else if (isRank(elevacion, 679.3, 439.2)) lineaMet = 2;
                else if (isRank(elevacion, 946.1, 679.4)) lineaMet = 3;
                else if (isRank(elevacion, 1150, 946.2)) lineaMet = 4;

            case 2:
                if (isRank(elevacion, 207.1, 0)) lineaMet = 0;
                else if (isRank(elevacion, 401.8, 207.2)) lineaMet = 1;
                else if (isRank(elevacion, 618.3, 401.9)) lineaMet = 2;
                else if (isRank(elevacion, 848.7, 618.4)) lineaMet = 3;
                else if (isRank(elevacion, 1089.9, 848.8)) lineaMet = 4;
            case 3:
                if (isRank(elevacion, 189.5, 0)) lineaMet = 0;
                else if (isRank(elevacion, 365.8, 189.6)) lineaMet = 1;
                else if (isRank(elevacion, 559, 365.9)) lineaMet = 2;
                else if (isRank(elevacion, 760, 559.1)) lineaMet = 3;
                else if (isRank(elevacion, 955.1, 760.1)) lineaMet = 4;
                else if (isRank(elevacion, 1150, 955.2)) lineaMet = 5;
            case 4:
                if (isRank(elevacion, 167.6, 0)) lineaMet = 0;
                else if (isRank(elevacion, 320.7, 167.7)) lineaMet = 1;
                else if (isRank(elevacion, 487.8, 320.8)) lineaMet = 2;
                else if (isRank(elevacion, 657, 487.9)) lineaMet = 3;
                else if (isRank(elevacion, 811.4, 657.1)) lineaMet = 4;
                else if (isRank(elevacion, 1052.9, 811.5)) lineaMet = 5;
            case 5:
                if (isRank(elevacion, 146.5, 0)) lineaMet = 0;
                else if (isRank(elevacion, 281.2, 146.6)) lineaMet = 1;
                else if (isRank(elevacion, 424.4, 281.3)) lineaMet = 2;
                else if (isRank(elevacion, 566.9, 424.5)) lineaMet = 3;
                else if (isRank(elevacion, 693.7, 567)) lineaMet = 4;
                else if (isRank(elevacion, 875.6, 693.8)) lineaMet = 5;
                else if (isRank(elevacion, 1143.7, 875.7)) lineaMet = 6;
            case 6:
                if (isRank(elevacion, 126.2, 0)) lineaMet = 0;
                else if (isRank(elevacion, 247.6, 126.3)) lineaMet = 1;
                else if (isRank(elevacion, 376.4, 247.7)) lineaMet = 2;
                else if (isRank(elevacion, 502, 376.5)) lineaMet = 3;
                else if (isRank(elevacion, 610.9, 502.1)) lineaMet = 4;
                else if (isRank(elevacion, 760.6, 611)) lineaMet = 5;
                else if (isRank(elevacion, 956.8, 760.7)) lineaMet = 6;
                else if (isRank(elevacion, 1185.1, 956.9)) lineaMet = 7;
            case 7:
                if (isRank(elevacion, 100.9, 0)) lineaMet = 0;
                else if (isRank(elevacion, 203.2, 101)) lineaMet = 1;
                else if (isRank(elevacion, 316.5, 203.3)) lineaMet = 2;
                else if (isRank(elevacion, 427.9, 316.6)) lineaMet = 3;
                else if (isRank(elevacion, 523.4, 428)) lineaMet = 4;
                else if (isRank(elevacion, 651.5, 523.5)) lineaMet = 5;
                else if (isRank(elevacion, 809.4, 651.6)) lineaMet = 6;
                else if (isRank(elevacion, 966.8, 809.5)) lineaMet = 7;
                else if (isRank(elevacion, 1143.7, 966.9)) lineaMet = 8;
        }
        return lineaMet;
    }

    @Override
    public double getTBDistanciaPorComplementario(double distancia, double intervalo, int carga) {
        double anguloSituacion = calculaAnguloCatetosMilesimas(intervalo, distancia);
        double anguloComplementario = 0;
        double distanciaPorComplementario;

        if (intervalo >= 0) {
            anguloComplementario = anguloSituacion * getTG_AnguloComp_Mas(distancia, carga);
        } else {
            anguloComplementario = anguloSituacion * getTG_AnguloComp_Menos(distancia, carga);
        }
        distanciaPorComplementario = anguloComplementario * getTFi5_ChangeRangeFor1Mil(distancia, carga);

        return distanciaPorComplementario;
    }

    @Override
    public double getTG_AnguloComp_Menos(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (rasante) {
                    return -2.5866666553E-20 * potencia(distancia, 6) + 2.29446152639159E-16 * potencia(distancia, 5) - 7.63487174667364E-13 * potencia(distancia, 4)
                            + 1.1741550027584E-09 * potencia(distancia, 3) - 8.17854188969735E-07 * potencia(distancia, 2) + 0.0000680987167065439 * distancia - 0.000175407466045385;

                } else {
                    return 0.000001138 * potencia(distancia, 2) - 0.00592100000000002 * distancia + 8.83800000000004;
                }
            case 2:
                if (rasante) {
                    return -3.988148155E-21 * potencia(distancia, 6) + 3.4411624017421E-17 * potencia(distancia, 5) - 1.01353276728806E-13 * potencia(distancia, 4)
                            + 1.0190668302295E-10 * potencia(distancia, 3) - 1.72096255510179E-08 * potencia(distancia, 2) - 0.0000271127098923074 * distancia + 0.000178864406009893;
                } else {
                    return 7.20000000000013E-07 * potencia(distancia, 2) - 0.00436200000000009 * distancia + 7.77200000000017;
                }

            case 3:
                if (rasante) {
                    return -9.24444445E-22 * potencia(distancia, 6) + 8.84102565385E-18 * potencia(distancia, 5) - 2.75931624610628E-14 * potencia(distancia, 4)
                            + 2.04778556257319E-11 * potencia(distancia, 3) + 1.60162081184329E-08 * potencia(distancia, 2) - 0.0000271167832739394 * distancia + 0.000412587496015249;
                } else {
                    return 1.90066666667849E-12 * potencia(distancia, 4) - 2.83820000001886E-08 * potencia(distancia, 3) + 0.000157926833334455 * potencia(distancia, 2)
                            - 0.387789500002956 * potencia(distancia, 1) + 355.480000002924;
                }
            case 4:
                if (rasante) {
                    return -2.40522876E-22 * potencia(distancia, 6) + 2.888687784054E-18 * potencia(distancia, 5) - 1.12626948329554E-14 * potencia(distancia, 4)
                            + 1.05581380006962E-11 * potencia(distancia, 3) + 8.15176759774716E-09 * potencia(distancia, 2) - 0.0000192971446986157 * potencia(distancia, 1) + 0.00038574700984384;
                } else {
                    return 1.78666666667648E-13 * potencia(distancia, 4) - 3.31200000001805E-09 * potencia(distancia, 3) + 0.000023011333333464 * potencia(distancia, 2)
                            - 0.0708500000004421 * potencia(distancia, 1) + 82.5630000005885;
                }

            case 5:
                if (rasante) {
                    return -2.47086342E-22 * potencia(distancia, 6) + 4.837064358763E-18 * potencia(distancia, 5) - 3.59856604208295E-14 * potencia(distancia, 4)
                            + 1.23435906607524E-10 * potencia(distancia, 3) - 1.89433806069457E-07 * potencia(distancia, 2) + 0.0000833005467590908 * potencia(distancia, 1) - 0.00487016127064521;
                } else {
                    return 4.9066666661872E-17 * potencia(distancia, 5) - 1.44466666650379E-12 * potencia(distancia, 4) + 1.69953333311928E-08 * potencia(distancia, 3)
                            - 0.0000997928333195422 * potencia(distancia, 2) + 0.292413099955926 * potencia(distancia, 1) - 340.962999943393;
                }
            case 6:
                if (rasante) {
                    return -5.9347721E-23 * potencia(distancia, 6) + 1.328372234148E-18 * potencia(distancia, 5) - 1.11635198311751E-14 * potencia(distancia, 4)
                            + 4.24967462449144E-11 * potencia(distancia, 3) - 7.26089839178136E-08 * potencia(distancia, 2) + 0.0000411485133340328 * potencia(distancia, 1) - 0.00316190541718697;
                } else {
                    return 5.51111093E-21 * potencia(distancia, 6) - 2.38933325353693E-16 * potencia(distancia, 5) + 4.31777763134229E-12 * potencia(distancia, 4)
                            - 4.15966652324878E-08 * potencia(distancia, 3) + 0.000225188203201171 * potencia(distancia, 2) - 0.649181876666723 * potencia(distancia, 1) + 779.279971149641;
                }

            case 7:
                if (rasante) {
                    return -2.5265331E-23 * potencia(distancia, 6) + 7.12772526252E-19 * potencia(distancia, 5) - 7.6241061064188E-15 * potencia(distancia, 4)
                            + 3.77106569989849E-11 * potencia(distancia, 3) - 8.41920537691769E-08 * potencia(distancia, 2) + 0.0000612120014793494 * potencia(distancia, 1) - 0.00727806068221071;
                } else {
                    return 9.688888771E-21 * potencia(distancia, 6) - 5.27189737019396E-16 * potencia(distancia, 5) + 1.19205810447696E-11 * potencia(distancia, 4)
                            - 1.43352985316896E-07 * potencia(distancia, 3) + 0.000966881236348558 * potencia(distancia, 2) - 3.46765109050654 * potencia(distancia, 1) + 5167.03851274272;
                }


            default:
                return 0;
        }
    }

    @Override
    public double getTG_AnguloComp_Mas(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (rasante) {
                    return 9.377777754E-21 * potencia(distancia, 6) - 6.805641000079E-17 * potencia(distancia, 5) + 1.70188033156232E-13 * potencia(distancia, 4)
                            - 1.54804776731298E-10 * potencia(distancia, 3) + 5.08563898632408E-08 * potencia(distancia, 2) + 0.000011950640534053 * distancia + 0.000125582352538345;

                } else {
                    return -1.15600000000001E-06 * potencia(distancia, 2) + 0.00601200000000005 * distancia - 8.95100000000008;
                }
            case 2:
                if (rasante) {
                    return 6.690370388E-21 * potencia(distancia, 6) - 7.0058803638879E-17 * potencia(distancia, 5) + 2.78908832910429E-13 * potencia(distancia, 4)
                            - 5.05852138863306E-10 * potencia(distancia, 3) + 4.05966534305913E-07 * potencia(distancia, 2) - 0.0000674109735072648 * distancia + 0.000290133278355142;
                } else {
                    return -7.20000000000007E-07 * potencia(distancia, 2) + 0.00435800000000006 * distancia - 7.76100000000012;
                }

            case 3:
                if (rasante) {
                    return 1.182222224E-21 * potencia(distancia, 6) - 1.1730256429369E-17 * potencia(distancia, 5) + 3.91581197579934E-14 * potencia(distancia, 4)
                            - 4.10686482414104E-11 * potencia(distancia, 3) + 2.57714083927441E-09 * potencia(distancia, 2) + 0.0000165394638944605 * distancia - 0.000340559523635875;
                } else {
                    return -2.20200000001372E-12 * potencia(distancia, 4) + 3.28966666668893E-08 * potencia(distancia, 3) - 0.000183105500001336 * potencia(distancia, 2)
                            + 0.449729833336873 * potencia(distancia, 1) - 412.181000003504;
                }
            case 4:
                if (rasante) {
                    return 1.119779843E-21 * potencia(distancia, 6) - 1.6775645013712E-17 * potencia(distancia, 5) + 9.30009262787851E-14 * potencia(distancia, 4)
                            - 2.30883287960626E-10 * potencia(distancia, 3) + 2.58386401439867E-07 * potencia(distancia, 2) - 0.0000958639574362152 * potencia(distancia, 1) + 0.00230566799376675;
                } else {
                    return -1.88666666667734E-13 * potencia(distancia, 4) + 3.50600000002078E-09 * potencia(distancia, 3) - 0.0000244148333334864 * potencia(distancia, 2)
                            + 0.0753345000005125 * potencia(distancia, 1) - 87.9010000006653;
                }

            case 5:
                if (rasante) {
                    return 2.60199517E-22 * potencia(distancia, 6) - 5.028133660683E-18 * potencia(distancia, 5) + 3.64073851667593E-14 * potencia(distancia, 4)
                            - 1.19290365991603E-10 * potencia(distancia, 3) + 1.73465123755497E-07 * potencia(distancia, 2) - 0.0000813993470574204 * potencia(distancia, 1) + 0.00398993681611159;
                } else {
                    return -5.0399999995975E-17 * potencia(distancia, 5) + 1.48199999985764E-12 * potencia(distancia, 4) - 1.740966666474E-08 * potencia(distancia, 3)
                            + 0.000102069499987312 * potencia(distancia, 2) - 0.2986024332921 * potencia(distancia, 1) + 347.617999946345;
                }
            case 6:
                if (rasante) {
                    return 6.7545744E-23 * potencia(distancia, 6) - 1.533942835695E-18 * potencia(distancia, 5) + 1.30805805979712E-14 * potencia(distancia, 4)
                            - 5.06600472271317E-11 * potencia(distancia, 3) + 8.82092562660454E-08 * potencia(distancia, 2) - 0.0000521952018890381 * potencia(distancia, 1) + 0.00401276458018174;
                } else {
                    return -8.444444158E-21 * potencia(distancia, 6) + 3.69999987168782E-16 * potencia(distancia, 5) - 6.74944420576866E-12 * potencia(distancia, 4)
                            + 6.55734976417211E-08 * potencia(distancia, 3) - 0.000357704098042499 * potencia(distancia, 2) + 1.03840546142413 * potencia(distancia, 1) - 1253.92195250105;
                }

            case 7:
                if (rasante) {
                    return 2.7840198E-23 * potencia(distancia, 6) - 7.89883790693E-19 * potencia(distancia, 5) + 8.49147912590999E-15 * potencia(distancia, 4)
                            - 4.22344651282342E-11 * potencia(distancia, 3) + 9.49605509039698E-08 * potencia(distancia, 2) - 0.0000705202281330308 * potencia(distancia, 1) + 0.00839224974617991;
                } else {
                    return -1.0044444322E-20 * potencia(distancia, 6) + 5.46635890631587E-16 * potencia(distancia, 5) - 1.2363187876948E-11 * potencia(distancia, 4)
                            + 1.48719626275019E-07 * potencia(distancia, 3) - 0.00100344456194532 * potencia(distancia, 2) + 3.60037787978424 * potencia(distancia, 1) - 5367.60742672795;
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
        return 1.51515151515134E-07 * potencia(deltaH_Bat_DMP, 2)
                - 0.00237878787878787 * deltaH_Bat_DMP + 0.00727272727272243;
    }

    @Override
    public double getTD_CorreccionDensidad(double deltaH_Bat_DMP) {
        return 7.57575757575398E-08 * potencia(deltaH_Bat_DMP, 2)
                - 0.00936515151515149 * deltaH_Bat_DMP - 0.027272727272738;
    }

    @Override
    public double getTE_Propellanttemperature(double temperature, int carga) {
        switch (carga) {
            case 1:
                return 0.000617130263104507 * potencia(temperature, 2) + 0.0594217338001054 * temperature - 1.54219841903608;
            case 2:
                return 0.000648112220036188 * potencia(temperature, 2) + 0.0702619890731938 * temperature - 1.79468548038986;
            case 3:
                return 0.000673558637514882 * potencia(temperature, 2) + 0.0815643154684725 * temperature - 2.05166053238936;
            case 4:
                return -3.40489963211277E-06 * potencia(temperature, 3) + 0.000753292020769308 * potencia(temperature, 2)
                        + 0.105602571253877 * temperature - 2.50976745410758;
            case 5:
                return 1.32703022491314E-07 * potencia(temperature, 4) - 0.0000054727608491715 * potencia(temperature, 3)
                        + 0.000363547938857298 * potencia(temperature, 2) + 0.133738292364612 * temperature - 2.89650733345135;
            case 6:
                return -7.34510726240335E-06 * potencia(temperature, 3) + 0.000988739676181516 * potencia(temperature, 2)
                        + 0.17217512569585 * temperature - 3.98249023408478;
            case 7:
                return -0.0000115189390048646 * potencia(temperature, 3) + 0.00121284197286055 * potencia(temperature, 2)
                        + 0.236394257011693 * temperature - 5.39005625320833;
            default:
                return 0;
        }
    }

    @Override
    public double getTFi1_DistanciaApuntada(double elevacion, int carga) {
        /**primero verifica si la distancia es alcanzada por la elevacion*/
        if (rasante) {
            if (elevacion > 800) {
                isPossible = false;
                return 0;
            }
        } else {
            if (elevacion < 800) {
                isPossible = false;
                return 0;
            }
        }
        /**ahora puede definir la distancia de acuerdo a la carga*/
        switch (carga) {
            case 1:
                if (rasante) {
                    return 3.36319680147078E-09 * potencia(elevacion, 4) - 6.14741634304833E-06 * potencia(elevacion, 3)
                            - 0.00206840377213524 * potencia(elevacion, 2) + 8.47335418545026 * elevacion - 6.43780576367862;

                } else {
                    return -1.23205200902587E-08 * potencia(elevacion, 4) + 0.0000499950581359654 * potencia(elevacion, 3)
                            - 0.0825125408443067 * potencia(elevacion, 2) + 61.4277800297245 * elevacion - 13233.8412779172;
                }
            case 2:
                if (rasante) {
                    return -1.82687256285487E-11 * potencia(elevacion, 5) + 3.8136887401969E-08 * potencia(elevacion, 4) - 0.0000290096767445736 * potencia(elevacion, 3)
                            + 0.00290935466199471 * potencia(elevacion, 2) + 9.47865819058643 * elevacion + 3.30299188685603;
                } else {
                    return -1.54265355832399E-08 * potencia(elevacion, 4) + 0.0000642271352355039 * potencia(elevacion, 3)
                            - 0.107465116920702 * potencia(elevacion, 2) + 80.5773118886009 * elevacion - 18013.8083394169;
                }

            case 3:
                if (rasante) {
                    return 1.01014291016572E-14 * potencia(elevacion, 6) - 3.68626867447647E-11 * potencia(elevacion, 5) + 4.99999690435626E-08 * potencia(elevacion, 4)
                            - 0.0000319905529533315 * potencia(elevacion, 3) + 0.0018816752372004 * potencia(elevacion, 2) + 11.4263338368037 * elevacion + 3.26759817078709;
                } else {
                    return -2.36870093216541E-13 * potencia(elevacion, 6) + 1.40767670399695E-09 * potencia(elevacion, 5) - 3.47178146466678E-06 * potencia(elevacion, 4)
                            + 0.00455099343755638 * potencia(elevacion, 3) - 3.35550576401571 * potencia(elevacion, 2) + 1323.76015143235 * elevacion - 213704.998195745;
                }
            case 4:
                if (rasante) {
                    return 2.76598106992696E-15 * potencia(elevacion, 6) - 3.44275443995624E-11 * potencia(elevacion, 5) + 6.62168195483814E-08 * potencia(elevacion, 4)
                            - 0.0000487310445187461 * potencia(elevacion, 3) + 0.0050167257704743 * potencia(elevacion, 2) + 14.2519712006615 * elevacion + 11.4072745135053;
                } else {
                    return -3.28125528931502E-16 * potencia(elevacion, 6) - 7.63013774085238E-11 * potencia(elevacion, 5) + 4.0174456061925E-07 * potencia(elevacion, 4)
                            - 0.000830403119329353 * potencia(elevacion, 3) + 0.835624510975793 * potencia(elevacion, 2) - 408.780625318802 * elevacion + 84170.7842113993;
                }

            case 5:
                if (rasante) {
                    return -2.04999960972792E-14 * potencia(elevacion, 6) + 4.08454056710118E-11 * potencia(elevacion, 5) - 2.65097485352417E-08 * potencia(elevacion, 4)
                            + 0.000004971148978683 * potencia(elevacion, 3) - 0.0121221580916426 * potencia(elevacion, 2) + 19.852454824344 * elevacion + 1.37310095503926;
                } else {
                    return -3.58545669452385E-13 * potencia(elevacion, 6) + 2.18200907827264E-09 * potencia(elevacion, 5) - 5.48533902726697E-06 * potencia(elevacion, 4)
                            + 0.00729031253475215 * potencia(elevacion, 3) - 5.41644463740087 * potencia(elevacion, 2) + 2138.48556873892 * elevacion - 343289.493310215;
                }
            case 6:
                if (rasante) {
                    return -1.24424777722052E-13 * potencia(elevacion, 6) + 3.25241124044797E-10 * potencia(elevacion, 5) - 3.31210234585932E-07 * potencia(elevacion, 4)
                            + 0.000165556202290418 * potencia(elevacion, 3) - 0.0565407456811045 * potencia(elevacion, 2) + 28.1817164228123 * elevacion + 14.9659388656727;
                } else {
                    return -2.52254702027302E-13 * potencia(elevacion, 6) + 1.61259450012326E-09 * potencia(elevacion, 5) - 4.25976643075142E-06 * potencia(elevacion, 4)
                            + 0.00594900232979589 * potencia(elevacion, 3) - 4.64833288018379 * potencia(elevacion, 2) + 1932.35644021658 * elevacion - 324882.362597685;
                }

            case 7:
                if (rasante) {
                    return -3.37860825020845E-13 * potencia(elevacion, 6) + 9.19077449020554E-10 * potencia(elevacion, 5) - 9.87947030482716E-07 * potencia(elevacion, 4)
                            + 0.000531210686077088 * potencia(elevacion, 3) - 0.164523377745809 * potencia(elevacion, 2) + 45.980560923228 * elevacion + 25.6281219022348;
                } else {
                    return 5.31443497895349E-13 * potencia(elevacion, 6) - 3.25467899687036E-09 * potencia(elevacion, 5) + 8.24782493432069E-06 * potencia(elevacion, 4)
                            - 0.0110735393994437 * potencia(elevacion, 3) + 8.28925397944131 * potencia(elevacion, 2) - 3272.76791961192 * elevacion + 543066.564992165;
                }


            default:
                return 0;
        }
    }

    @Override
    public double getTFi2_Elevacion(double distancia, int carga) {

        double elevacion = 0;


        try {
            if (rasante) {
                while (getTFi1_DistanciaApuntada(elevacion + 100, carga) < distancia) {
                    elevacion = elevacion + 100;
                    if (elevacion > 800) return 0;

                }
                while (getTFi1_DistanciaApuntada(elevacion + 10, carga) < distancia) {
                    elevacion = elevacion + 10;
                    if (elevacion > 800) return 0;

                }
                while (getTFi1_DistanciaApuntada(elevacion + 1, carga) < distancia) {
                    elevacion = elevacion + 1;
                    if (elevacion > 800) return 0;

                }
                while (getTFi1_DistanciaApuntada(elevacion, carga) < distancia) {
                    elevacion = elevacion + 0.1;
                    if (elevacion > 800) return 0;

                }

            } else {
                elevacion = 1300;
                while (getTFi1_DistanciaApuntada(elevacion - 100, carga) < distancia) {
                    elevacion = elevacion - 100;
                    if (elevacion < 800) return 0;
                }
                while (getTFi1_DistanciaApuntada(elevacion - 10, carga) < distancia) {
                    elevacion = elevacion - 10;
                    if (elevacion < 800) return 0;
                }
                while (getTFi1_DistanciaApuntada(elevacion - 1, carga) < distancia) {
                    elevacion = elevacion - 1;
                    if (elevacion < 800) return 0;
                }
                while (getTFi1_DistanciaApuntada(elevacion, carga) < distancia) {
                    elevacion = elevacion - 0.1;
                    if (elevacion < 800) return 0;
                }
            }
        } catch (Exception e) {
            isPossible = false;
            System.out.println("isPossible = " + isPossible);
            System.out.println("AmmunitionM1.getTFi2_Elevacion");
            e.printStackTrace();
        }
        return elevacion;
    }

    @Override
    public double getTFi4_HeighExplosure10m(double distancia, int carga) {
        switch (carga) {
            case 1:
                if (isRasante()) {
                    return -1.0068060603E-20 * potencia(distancia, 6) + 1.20486701970957E-16 * potencia(distancia, 5) - 5.35759244141552E-13 * potencia(distancia, 4)
                            + 1.00782175914975E-09 * potencia(distancia, 3) - 3.79833838520985E-07 * potencia(distancia, 2) - 0.00123785301003625 * distancia + 1.49632392681948;
                } else {
                    return 1.08506945431E-19 * potencia(distancia, 6) - 1.81790867104899E-15 * potencia(distancia, 5) + 1.26335471328255E-11 * potencia(distancia, 4)
                            - 4.66128537680374E-08 * potencia(distancia, 3) + 0.0000962982442656636 * potencia(distancia, 2) - 0.105614600508505 * distancia + 48.0996829400049;
                }

            case 2:
                if (isRasante()) {
                    return -1.4808091242E-20 * potencia(distancia, 6) + 2.23272806881147E-16 * potencia(distancia, 5) - 1.32425964321366E-12 * potencia(distancia, 4)
                            + 3.87601119842608E-09 * potencia(distancia, 3) - 5.66089143691186E-06 * potencia(distancia, 2) + 0.00340382694525684 * distancia + 0.0297668181252002;
                } else {
                    return -1.7875962093E-19 * potencia(distancia, 6) + 3.729148623281E-15 * potencia(distancia, 5) - 3.21892931855652E-11 * potencia(distancia, 4)
                            + 1.47133669040619E-07 * potencia(distancia, 3) - 0.000375553722343794 * potencia(distancia, 2) + 0.50748816576594 * distancia - 283.576627831237;
                }
            case 3:
                if (isRasante()) {
                    return -4.149761094E-21 * potencia(distancia, 6) + 7.3093780236898E-17 * potencia(distancia, 5) - 5.05033105229392E-13 * potencia(distancia, 4)
                            + 1.71350274911943E-09 * potencia(distancia, 3) - 2.86384330188272E-06 * potencia(distancia, 2) + 0.00185134348772702 * distancia + 0.287656136215804;
                } else {
                    return 2.2231717361E-20 * potencia(distancia, 6) - 5.41495013352619E-16 * potencia(distancia, 5) + 5.45630213018716E-12 * potencia(distancia, 4)
                            - 2.91040078862883E-08 * potencia(distancia, 3) + 0.0000866545696547374 * potencia(distancia, 2) - 0.136534506352121 * distancia + 88.986697593145;
                }
            case 4:
                if (isRasante()) {
                    return -2.37283822E-22 * potencia(distancia, 6) + 5.911451824409E-18 * potencia(distancia, 5) - 5.75171474186091E-14 * potencia(distancia, 4)
                            + 2.70271156551666E-10 * potencia(distancia, 3) - 5.91961752291828E-07 * potencia(distancia, 2) + 0.000348762283325699 * distancia + 0.553024035083254;
                } else {
                    return 3.36311227E-21 * potencia(distancia, 6) - 9.6352630998548E-17 * potencia(distancia, 5) + 1.14460182095946E-12 * potencia(distancia, 4)
                            - 7.21645970896178E-09 * potencia(distancia, 3) + 0.0000254684451740783 * potencia(distancia, 2) - 0.0477063869559001 * distancia + 37.1055140660767;
                }
            case 5:
                if (isRasante()) {
                    return -3.84733289E-22 * potencia(distancia, 6) + 9.967445363935E-18 * potencia(distancia, 5) - 9.90696275266168E-14 * potencia(distancia, 4)
                            + 4.6478454521455E-10 * potencia(distancia, 3) - 9.80525606243459E-07 * potencia(distancia, 2) + 0.000493926169465753 * distancia + 0.855560826157244;
                } else {
                    return -2.83852841E-21 * potencia(distancia, 6) + 1.05236580912494E-16 * potencia(distancia, 5) - 1.61570711090395E-12 * potencia(distancia, 4)
                            + 1.31501887678471E-08 * potencia(distancia, 3) - 0.0000598465796465441 * potencia(distancia, 2) + 0.144411687231635 * distancia - 144.322352089232;
                }
            case 6:
                if (isRasante()) {
                    return 6.1797429E-23 * potencia(distancia, 6) - 2.23606835361E-18 * potencia(distancia, 5) + 3.27052849513703E-14 * potencia(distancia, 4)
                            - 2.4772749851075E-10 * potencia(distancia, 3) + 1.03174650950328E-06 * potencia(distancia, 2) - 0.0022978330060423 * distancia + 2.37217061597657;
                } else {
                    return 8.39213511E-22 * potencia(distancia, 6) - 3.7767605768326E-17 * potencia(distancia, 5) + 7.05198355173586E-13 * potencia(distancia, 4)
                            - 6.9927187579208E-09 * potencia(distancia, 3) + 0.000038836502293618 * potencia(distancia, 2) - 0.114542535795522 * distancia + 140.198038303863;
                }
            case 7:
                if (isRasante()) {
                    return -1.9145411E-23 * potencia(distancia, 6) + 7.73974722453E-19 * potencia(distancia, 5) - 1.21072720908006E-14 * potencia(distancia, 4)
                            + 9.00635197734531E-11 * potencia(distancia, 3) - 3.00830337250608E-07 * potencia(distancia, 2) + 0.000210934929734598 * distancia + 0.788071654498291;
                } else {
                    return 3.75529894E-22 * potencia(distancia, 6) - 2.0694813330238E-17 * potencia(distancia, 5) + 4.72110909517952E-13 * potencia(distancia, 4)
                            - 5.70704838001133E-09 * potencia(distancia, 3) + 0.0000385590534681184 * potencia(distancia, 2) - 0.138077408795898 * distancia + 204.797500201696;
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
                    return -2.21607025401E-19 * potencia(distancia, 6) + 2.29971512054264E-15 * potencia(distancia, 5) - 9.01502318508738E-12 * potencia(distancia, 4)
                            + 1.66969776532179E-08 * potencia(distancia, 3) - 0.0000156146444882001 * potencia(distancia, 2) + 0.00644184545948235 * distancia + 7.16973878811385;
                } else {
                    return -1.085069455E-17 * potencia(distancia, 6) + 1.83794072353403E-13 * potencia(distancia, 5) - 1.2924011885131E-09 * potencia(distancia, 4)
                            + 4.82532966926326E-06 * potencia(distancia, 3) - 0.0100798863514568 * potencia(distancia, 2) + 11.1563286497319 * distancia - 5096.92109564308;
                }

            case 2:
                if (isRasante()) {
                    return -7.5658362124E-20 * potencia(distancia, 6) + 8.12647658523727E-16 * potencia(distancia, 5) - 2.98881315070733E-12 * potencia(distancia, 4)
                            + 3.89375947709409E-09 * potencia(distancia, 3) - 4.69727755774272E-08 * potencia(distancia, 2) - 0.0033630508577752 * distancia + 10.7801857509586;
                } else {
                    return -5.787037017518E-18 * potencia(distancia, 6) + 1.08039529513699E-13 * potencia(distancia, 5) - 8.30172717385853E-10 * potencia(distancia, 4)
                            + 3.35555311202144E-06 * potencia(distancia, 3) - 0.00750993647363398 * potencia(distancia, 2) + 8.79723549559264 * distancia - 4186.79826157677;
                }
            case 3:
                if (isRasante()) {
                    return -1.4137137317E-20 * potencia(distancia, 6) + 1.69323764053227E-16 * potencia(distancia, 5) - 6.88388054811572E-13 * potencia(distancia, 4)
                            + 9.27424887700503E-10 * potencia(distancia, 3) + 1.94747421800034E-07 * potencia(distancia, 2) - 0.00210520019544447 * distancia + 12.4532464108997;
                } else {
                    return 1.06941356149E-19 * potencia(distancia, 6) - 2.53454027477604E-15 * potencia(distancia, 5) + 2.32507090013843E-11 * potencia(distancia, 4)
                            - 1.05551301862895E-07 * potencia(distancia, 3) + 0.000246458213472897 * potencia(distancia, 2) - 0.272222184816972 * distancia + 111.98803161879;
                }
            case 4:
                if (isRasante()) {
                    return -3.255255898E-21 * potencia(distancia, 6) + 2.3058068584988E-17 * potencia(distancia, 5) + 1.59826926338131E-13 * potencia(distancia, 4)
                            - 1.85499797993805E-09 * potencia(distancia, 3) + 5.17063775641639E-06 * potencia(distancia, 2) - 0.00558477385409079 * distancia + 16.2551705744699;
                } else {
                    return -3.91101508216E-19 * potencia(distancia, 6) + 1.06003058838167E-14 * potencia(distancia, 5) - 1.18475422627552E-10 * potencia(distancia, 4)
                            + 6.97826689943011E-07 * potencia(distancia, 3) - 0.00228013851753226 * potencia(distancia, 2) + 3.90657786870928 * distancia - 2717.84305809134;
                }
            case 5:
                if (isRasante()) {
                    return -2.342568675E-21 * potencia(distancia, 6) + 5.1751344862481E-17 * potencia(distancia, 5) - 4.34600027175706E-13 * potencia(distancia, 4)
                            + 1.70209544112654E-09 * potencia(distancia, 3) - 3.16838943977713E-06 * potencia(distancia, 2) + 0.00112699566319105 * distancia + 19.4257224814376;
                } else {
                    return -2.52035115284E-19 * potencia(distancia, 6) + 9.08420243504342E-15 * potencia(distancia, 5) - 1.35589959629562E-10 * potencia(distancia, 4)
                            + 1.07238134702144E-06 * potencia(distancia, 3) - 0.00473902618191498 * potencia(distancia, 2) + 11.0917684420493 * distancia - 10724.840110303;
                }
            case 6:
                if (isRasante()) {
                    return -5.6044819E-22 * potencia(distancia, 6) + 1.3180989265671E-17 * potencia(distancia, 5) - 1.05916958912086E-13 * potencia(distancia, 4)
                            + 2.43691352759169E-10 * potencia(distancia, 3) + 8.44548657551931E-07 * potencia(distancia, 2) - 0.00628694874300692 * distancia + 29.771057842969;
                } else {
                    return -1.73075244289E-19 * potencia(distancia, 6) + 7.70538705793279E-15 * potencia(distancia, 5) - 1.42161164698504E-10 * potencia(distancia, 4)
                            + 1.39106276533285E-06 * potencia(distancia, 3) - 0.00761364229307472 * potencia(distancia, 2) + 22.0975931494562 * distancia - 26549.3174318233;
                }
            case 7:
                if (isRasante()) {
                    return -4.16477751E-22 * potencia(distancia, 6) + 1.4312055195491E-17 * potencia(distancia, 5) - 1.88893311381286E-13 * potencia(distancia, 4)
                            + 1.13071049177634E-09 * potencia(distancia, 3) - 2.33997057868814E-06 * potencia(distancia, 2) - 0.00597690609314086 * distancia + 47.4066126057629;
                } else {
                    return -5.827774997E-20 * potencia(distancia, 6) + 3.15750809307344E-15 * potencia(distancia, 5) - 7.0947157380116E-11 * potencia(distancia, 4)
                            + 8.46144547655278E-07 * potencia(distancia, 3) - 0.0056492291968881 * potencia(distancia, 2) + 20.0168633563536 * distancia - 29381.1905173029;
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
                    return 2.640156529578E-18 * potencia(distancia, 6) - 2.65212584925704E-14 * potencia(distancia, 5) + 1.0254099994664E-10 * potencia(distancia, 4)
                            - 1.89700331993986E-07 * potencia(distancia, 3) + 0.000172211748377602 * potencia(distancia, 2) - 0.0692820088247161 * distancia + 10.8843099464362;
                } else {
                    return 1.84461807099979E-16 * potencia(distancia, 6) - 3.04236781532122E-12 * potencia(distancia, 5) + 2.08710605568531E-08 * potencia(distancia, 4)
                            - 0.0000761878376422113 * potencia(distancia, 3) + 0.156023474164382 * potencia(distancia, 2) - 169.883182873305 * distancia + 76816.540753831;
                }

            case 2:
                if (isRasante()) {
                    return 1.349672475606E-18 * potencia(distancia, 6) - 1.59306307215218E-14 * potencia(distancia, 5) + 7.1804246313173E-11 * potencia(distancia, 4)
                            - 1.54180670906944E-07 * potencia(distancia, 3) + 0.000160322129639354 * potencia(distancia, 2) - 0.0701830500028952 * distancia + 10.5338492752273;
                } else {
                    return -2.8935184813699E-17 * potencia(distancia, 6) + 6.04300206065467E-13 * potencia(distancia, 5) - 5.21656866755282E-09 * potencia(distancia, 4)
                            + 0.0000238414308469297 * potencia(distancia, 3) - 0.06087392425205 * potencia(distancia, 2) + 82.3572863122314 * distancia - 46102.1703941693;
                }
            case 3:
                if (isRasante()) {
                    return 3.65953970701E-19 * potencia(distancia, 6) - 5.0058427901312E-15 * potencia(distancia, 5) + 2.6215515275319E-11 * potencia(distancia, 4)
                            - 6.52772535971206E-08 * potencia(distancia, 3) + 0.0000784912398622229 * potencia(distancia, 2) - 0.0393363003622296 * distancia + 6.92146288600877;
                } else {
                    return 2.760968408432E-18 * potencia(distancia, 6) - 5.16755297505365E-14 * potencia(distancia, 5) + 3.92017910556465E-10 * potencia(distancia, 4)
                            - 0.0000015306734758178 * potencia(distancia, 3) + 0.00320586504057086 * potencia(distancia, 2) - 3.33825688393842 * distancia + 1286.14940737993;
                }
            case 4:
                if (isRasante()) {
                    return 1.11504816616E-19 * potencia(distancia, 6) - 1.90816862580981E-15 * potencia(distancia, 5) + 1.24296169904512E-11 * potencia(distancia, 4)
                            - 3.8225935813743E-08 * potencia(distancia, 3) + 0.0000560703534791169 * potencia(distancia, 2) - 0.0334233596285224 * distancia + 6.81542940868011;
                } else {
                    return -3.592423527691E-18 * potencia(distancia, 6) + 1.12540939777051E-13 * potencia(distancia, 5) - 1.44701577798734E-09 * potencia(distancia, 4)
                            + 9.79277460132066E-06 * potencia(distancia, 3) - 0.036840867815644 * potencia(distancia, 2) + 73.129608296296 * distancia - 59877.7160981062;
                }
            case 5:
                if (isRasante()) {
                    return 3.5718222978E-20 * potencia(distancia, 6) - 7.65849835690081E-16 * potencia(distancia, 5) + 6.2341763585019E-12 * potencia(distancia, 4)
                            - 2.39041005466495E-08 * potencia(distancia, 3) + 0.0000435443581851298 * potencia(distancia, 2) - 0.0320536624122207 * distancia + 6.91077068702501;
                } else {
                    return 1.614566695838E-18 * potencia(distancia, 6) - 5.66063534102314E-14 * potencia(distancia, 5) + 8.23463898375756E-10 * potencia(distancia, 4)
                            - 6.36136335804757E-06 * potencia(distancia, 3) + 0.0275219433516567 * potencia(distancia, 2) - 63.2239963485275 * distancia + 60251.8271548131;
                }
            case 6:
                if (isRasante()) {
                    return 2.509838242E-21 * potencia(distancia, 6) - 6.3379489680257E-17 * potencia(distancia, 5) + 6.05832356137003E-13 * potencia(distancia, 4)
                            - 2.70781933886691E-09 * potencia(distancia, 3) + 5.66328188732911E-06 * potencia(distancia, 2) - 0.00394054670388705 * distancia + 0.925534244704256;
                } else {
                    return 5.5715239577E-19 * potencia(distancia, 6) - 2.507334409703E-14 * potencia(distancia, 5) + 4.68996419724587E-10 * potencia(distancia, 4)
                            - 4.66681419003856E-06 * potencia(distancia, 3) + 0.026053226259472 * potencia(distancia, 2) - 77.3647690581724 * distancia + 95466.8615159155;
                }
            case 7:
                if (isRasante()) {
                    return 1.387703871E-21 * potencia(distancia, 6) - 4.3153320534313E-17 * potencia(distancia, 5) + 5.07000334958229E-13 * potencia(distancia, 4)
                            - 2.78931435533259E-09 * potencia(distancia, 3) + 7.20954503482991E-06 * potencia(distancia, 2) - 0.00712533808113891 * distancia + 1.93716299415839;
                } else {
                    return 1.06301190557E-19 * potencia(distancia, 6) - 5.61668576388127E-15 * potencia(distancia, 5) + 1.23155338390331E-10 * potencia(distancia, 4)
                            - 1.43428705049543E-06 * potencia(distancia, 3) + 0.00935703891972337 * potencia(distancia, 2) - 32.4217520326051 * distancia + 46628.6055878658;
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
                    return 2.70110814556E-19 * potencia(distancia, 6) - 2.83911324847063E-15 * potencia(distancia, 5) + 1.14730068562645E-11 * potencia(distancia, 4)
                            - 2.20997074675227E-08 * potencia(distancia, 3) + 0.0000208532535579603 * potencia(distancia, 2) - 0.00360665938862475 * distancia + 1.11610511268607;
                } else {
                    return 3.255208409227E-18 * potencia(distancia, 6) - 6.17487993915561E-14 * potencia(distancia, 5) + 4.79667477349612E-10 * potencia(distancia, 4)
                            - 1.95878408993826E-06 * potencia(distancia, 3) + 0.00444239470571065 * potencia(distancia, 2) - 5.31352748618853 * distancia + 2660.53497399914;
                }

            case 2:
                if (isRasante()) {
                    return 1.00218929697E-19 * potencia(distancia, 6) - 1.20522386526251E-15 * potencia(distancia, 5) + 5.56447985020076E-12 * potencia(distancia, 4)
                            - 1.21843998365158E-08 * potencia(distancia, 3) + 0.0000130019764310646 * potencia(distancia, 2) - 0.00135304089115069 * distancia + 0.815582260755585;
                } else {
                    return -2.2075249738085E-17 * potencia(distancia, 6) + 4.43369764334041E-13 * potencia(distancia, 5) - 3.69132134842653E-09 * potencia(distancia, 4)
                            + 0.000016304383740472 * potencia(distancia, 3) - 0.0402925599828692 * potencia(distancia, 2) + 52.8181527157646 * distancia - 28648.7621009849;
                }
            case 3:
                if (isRasante()) {
                    return 3.0771268582E-20 * potencia(distancia, 6) - 4.09431264109195E-16 * potencia(distancia, 5) + 2.05159260561299E-12 * potencia(distancia, 4)
                            - 4.69348734844725E-09 * potencia(distancia, 3) + 4.93711130788999E-06 * potencia(distancia, 2) + 0.0023188471346316 * distancia + 0.0956338526302716;
                } else {
                    return -4.057708769683E-18 * potencia(distancia, 6) + 9.47768691780948E-14 * potencia(distancia, 5) - 9.1780555817598E-10 * potencia(distancia, 4)
                            + 4.71556448639644E-06 * potencia(distancia, 3) - 0.0135555140869466 * potencia(distancia, 2) + 20.6677404136466 * distancia - 13009.9045574419;
                }
            case 4:
                if (isRasante()) {
                    return 1.0115977311E-20 * potencia(distancia, 6) - 1.70616085560917E-16 * potencia(distancia, 5) + 1.08844565355007E-12 * potencia(distancia, 4)
                            - 3.19020718517323E-09 * potencia(distancia, 3) + 4.31202928141694E-06 * potencia(distancia, 2) + 0.00160056361210081 * distancia + 0.156076561113878;
                } else {
                    return -2.10220558966E-18 * potencia(distancia, 6) + 6.17230424987502E-14 * potencia(distancia, 5) - 7.51371718980967E-10 * potencia(distancia, 4)
                            + 4.85346183714139E-06 * potencia(distancia, 3) - 0.0175442012486734 * potencia(distancia, 2) + 33.6466128778527 * distancia - 26693.8826579043;
                }
            case 5:
                if (isRasante()) {
                    return 2.521229983E-21 * potencia(distancia, 6) - 5.2488227502932E-17 * potencia(distancia, 5) + 4.13723005634404E-13 * potencia(distancia, 4)
                            - 1.5026745758893E-09 * potencia(distancia, 3) + 2.57093306965661E-06 * potencia(distancia, 2) + 0.00163904150770122 * distancia + 0.161416127870325;
                } else {
                    return -4.76288291189E-19 * potencia(distancia, 6) + 1.74579241231617E-14 * potencia(distancia, 5) - 2.65393063082744E-10 * potencia(distancia, 4)
                            + 2.14144437065913E-06 * potencia(distancia, 3) - 0.00967246554776199 * potencia(distancia, 2) + 23.1850887175648 * distancia - 22981.2971880376;
                }
            case 6:
                if (isRasante()) {
                    return 9.63961577E-22 * potencia(distancia, 6) - 2.4242193889555E-17 * potencia(distancia, 5) + 2.31684037683374E-13 * potencia(distancia, 4)
                            - 1.03238061045644E-09 * potencia(distancia, 3) + 0.0000022302372859051 * potencia(distancia, 2) + 0.00108869759614549 * distancia + 0.208565924243885;
                } else {
                    return -2.45834091362E-19 * potencia(distancia, 6) + 1.10830377787693E-14 * potencia(distancia, 5) - 2.07368030489699E-10 * potencia(distancia, 4)
                            + 2.06081391454306E-06 * potencia(distancia, 3) - 0.0114718931765887 * potencia(distancia, 2) + 33.912484617828 * distancia - 41522.7187107657;
                }
            case 7:
                if (isRasante()) {
                    return 3.7269494E-22 * potencia(distancia, 6) - 1.1545577774948E-17 * potencia(distancia, 5) + 1.37088354959733E-13 * potencia(distancia, 4)
                            - 7.77125996311799E-10 * potencia(distancia, 3) + 2.25516703257008E-06 * potencia(distancia, 2) - 0.000238489791762031 * distancia + 0.641573657398236;
                } else {
                    return -8.9943933156E-20 * potencia(distancia, 6) + 4.9017479678407E-15 * potencia(distancia, 5) - 1.10840569615769E-10 * potencia(distancia, 4)
                            + 1.33096606669842E-06 * potencia(distancia, 3) - 0.00895043470656406 * potencia(distancia, 2) + 31.9566698621149 * distancia - 47249.8962913868;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getTFi8_Drift(double tiempoVuelo, int carga) {
        if (isLG) {
            switch (carga) {
                case 1:
                    return -1.4395073928003E-07*potencia(tiempoVuelo,6 )+ 0.000022503896880785*potencia(tiempoVuelo,5 )
                            - 0.00110646616073245*potencia(tiempoVuelo,4 )+ 0.022364070396807*potencia(tiempoVuelo,3 )
                            - 0.165578817205193*potencia(tiempoVuelo,2 )+ 0.778463929358098*potencia(tiempoVuelo, 1)- 0.23438897255066;
                case 2:
                    return 1.6267144061291E-07*potencia(tiempoVuelo,6 )- 5.41127183647655E-06*potencia(tiempoVuelo,5 )
                            - 0.000239820185085671*potencia(tiempoVuelo,4 )+ 0.0105479504478229*potencia(tiempoVuelo,3 )
                            - 0.0611442715785684*potencia(tiempoVuelo,2 )- 0.157073309914955*potencia(tiempoVuelo,1 )+ 0.82544338859591;
                case 3:
                    return 6.03772228194646E-07*potencia(tiempoVuelo,6 )- 0.0000663856189280065*potencia(tiempoVuelo,5 )
                            + 0.00272444367373959*potencia(tiempoVuelo,4 )- 0.0523005289074987*potencia(tiempoVuelo,3 )
                            + 0.503024546713184*potencia(tiempoVuelo,2 )- 1.84912165206977*potencia(tiempoVuelo,1 )+ 2.12959510772586;
                case 4:
                    return 3.63534274058774E-07*potencia(tiempoVuelo,6 )- 0.0000457018323224062*potencia(tiempoVuelo,5 )
                            + 0.0021294345864546*potencia(tiempoVuelo,4 )- 0.0455026466051925*potencia(tiempoVuelo,3 )
                            + 0.461403758028633*potencia(tiempoVuelo,2 )- 1.65665032657866*potencia(tiempoVuelo,1 )+ 1.85753227671236;
                case 5:
                    return 1.17088122029638E-07*potencia(tiempoVuelo,6 )- 0.0000174146923755843*potencia(tiempoVuelo,5 )
                            + 0.000975793286236678*potencia(tiempoVuelo,4 )- 0.0252592492943348*potencia(tiempoVuelo,3 )
                            + 0.303666928968814*potencia(tiempoVuelo,2 )- 1.16120747059288*potencia(tiempoVuelo,1 )+ 1.42333620694762;
                case 6:
                    return 9.18615040484613E-08*potencia(tiempoVuelo,6 )- 0.0000154497871901816*potencia(tiempoVuelo,5 )
                            + 0.000978437639323604*potencia(tiempoVuelo,4 )- 0.0286662252260489*potencia(tiempoVuelo,3 )
                            + 0.388740987947485*potencia(tiempoVuelo,2 )- 1.79693855935606*potencia(tiempoVuelo,1 )+ 2.3600642521153;
                case 7:
                    return 4.70099586812132E-08*potencia(tiempoVuelo,6 )- 8.85717869308048E-06*potencia(tiempoVuelo,5 )
                            + 0.000629878530526398*potencia(tiempoVuelo,4 )- 0.0207755089970992*potencia(tiempoVuelo,3 )
                            + 0.318667017102965*potencia(tiempoVuelo,2 )- 1.64644479003389*potencia(tiempoVuelo,1 )+ 2.27525840430781;
                default:
                    return 0;
            }
        }
        else {///Si en cambio es L119
            switch (carga) {
                case 1:
                    return -1.66115814320689E-06*potencia(tiempoVuelo,6 )+ 0.000177137434979824*potencia(tiempoVuelo,5 )
                            - 0.0068617217048888*potencia(tiempoVuelo,4 )+ 0.120632244607337*potencia(tiempoVuelo,3 )
                            - 0.953935544828042*potencia(tiempoVuelo,2 )+ 3.30993111819791*potencia(tiempoVuelo, 1)- 2.09498076599551;

                case 2:
                    return 4.59361314930629E-07*potencia(tiempoVuelo,6 )- 0.0000554529485540356*potencia(tiempoVuelo,5 )
                            + 0.00261370375383197*potencia(tiempoVuelo,4 )- 0.0571472557920452*potencia(tiempoVuelo,3 )
                            + 0.577375275304391*potencia(tiempoVuelo,2 )- 1.89344474983329*potencia(tiempoVuelo,1 )+ 1.96875885128814;
                case 3:
                    return 1.15685279224176E-07*potencia(tiempoVuelo,6 )- 0.0000106126696672162*potencia(tiempoVuelo,5 )
                            + 0.000387227943133256*potencia(tiempoVuelo,4 )- 0.00726646332153198*potencia(tiempoVuelo,3 )
                            + 0.0798422817730946*potencia(tiempoVuelo,2 )- 0.037424082110244*potencia(tiempoVuelo,1 )+ 0.387333609894448;
                case 4:
                    return 3.89330359531342E-07*potencia(tiempoVuelo,6 )- 0.0000475048731471209*potencia(tiempoVuelo,5 )
                            + 0.00213846241441265*potencia(tiempoVuelo,4 )- 0.0436649473551541*potencia(tiempoVuelo,3 )
                            + 0.412736085552348*potencia(tiempoVuelo,2 )- 1.23104593660521*potencia(tiempoVuelo,1 )+ 1.23028444314916;
                case 5:
                    return 1.69884767854418E-07*potencia(tiempoVuelo,6 )- 0.0000231397300771109*potencia(tiempoVuelo,5 )
                            + 0.00116362799098936*potencia(tiempoVuelo,4 )- 0.0265956644633094*potencia(tiempoVuelo,3 )
                            + 0.281874381314257*potencia(tiempoVuelo,2 )- 0.907084102321381*potencia(tiempoVuelo,1 )+ 0.968658599674442;
                case 6:
                    return -1.36740985873761E-09*potencia(tiempoVuelo,6 )+ 0.0000003758716459871*potencia(tiempoVuelo,5 )
                            - 0.0000274963861462039*potencia(tiempoVuelo,4 )+ 0.000861550280652347*potencia(tiempoVuelo,3 )
                            - 0.00732561734452513*potencia(tiempoVuelo,2 )+ 0.241555833338901*potencia(tiempoVuelo,1 )- 0.0869717470407192;
                case 7:
                    return 1.18425668739746E-08*potencia(tiempoVuelo,6 )- 0.0000016879071724628*potencia(tiempoVuelo,5 )
                            + 0.0000905045266153617*potencia(tiempoVuelo,4 )- 0.00223563950044502*potencia(tiempoVuelo,3 )
                            + 0.029860251569982*potencia(tiempoVuelo,2 )+ 0.0488331257836711*potencia(tiempoVuelo,1 )+ 0.038758149405195;
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
                    return 9.473404629E-21 * potencia(distancia, 6) - 1.05022185410565E-16 * potencia(distancia, 5) + 4.43176172419323E-13 * potencia(distancia, 4)
                            - 8.77910582827941E-10 * potencia(distancia, 3) + 8.2895906632194E-07 * potencia(distancia, 2) - 0.000286604944099761 * distancia + 0.0300428367954187;
                } else {
                    return -3.25520833799E-19 * potencia(distancia, 6) + 5.17327725199471E-15 * potencia(distancia, 5) - 3.35737180117409E-11 * potencia(distancia, 4)
                            + 1.13112889966932E-07 * potencia(distancia, 3) - 0.000206032842207478 * potencia(distancia, 2) + 0.187041362860675 * distancia - 60.5077771364582;
                }

            case 2:
                if (isRasante()) {
                    return 2.55395341E-21 * potencia(distancia, 6) - 3.2056375239597E-17 * potencia(distancia, 5) + 1.52145994676783E-13 * potencia(distancia, 4)
                            - 3.35313961320732E-10 * potencia(distancia, 3) + 3.54099223648367E-07 * potencia(distancia, 2) - 0.000125558830398768 * distancia + 0.0257456637990953;
                } else {
                    return -1.06889765303E-19 * potencia(distancia, 6) + 1.99745415614903E-15 * potencia(distancia, 5) - 1.53398302783073E-11 * potencia(distancia, 4)
                            + 6.16644284502535E-08 * potencia(distancia, 3) - 0.00013562314752234 * potencia(distancia, 2) + 0.151719549528227 * distancia - 63.464168574148;
                }
            case 3:
                if (isRasante()) {
                    return 6.91123983E-22 * potencia(distancia, 6) - 9.814742249967E-18 * potencia(distancia, 5) + 5.19537810417694E-14 * potencia(distancia, 4)
                            - 1.23697516924826E-10 * potencia(distancia, 3) + 1.33976887719406E-07 * potencia(distancia, 2) - 0.000018900031362179 * distancia + 0.00453235618033432;
                } else {
                    return -6.6608238514E-20 * potencia(distancia, 6) + 1.55270724936224E-15 * potencia(distancia, 5) - 1.49534300700124E-11 * potencia(distancia, 4)
                            + 7.60372389270951E-08 * potencia(distancia, 3) - 0.000214798592887482 * potencia(distancia, 2) + 0.318051775820014 * distancia - 190.086307159922;
                }
            case 4:
                if (isRasante()) {
                    return 2.49931639E-22 * potencia(distancia, 6) - 4.62226456672E-18 * potencia(distancia, 5) + 3.18772561684426E-14 * potencia(distancia, 4)
                            - 9.81502236790279E-11 * potencia(distancia, 3) + 1.3006988206811E-07 * potencia(distancia, 2) - 0.0000248636610558073 * distancia + 0.0144200181718475;
                } else {
                    return -2.8601038798E-20 * potencia(distancia, 6) + 8.33507182469738E-16 * potencia(distancia, 5) - 1.00397890617782E-11 * potencia(distancia, 4)
                            + 6.39335276736413E-08 * potencia(distancia, 3) - 0.000226749623898504 * potencia(distancia, 2) + 0.423608581182673 * distancia - 323.151132117318;
                }
            case 5:
                if (isRasante()) {
                    return 2.760962E-23 * potencia(distancia, 6) - 5.51163433939E-19 * potencia(distancia, 5) + 4.07631473116017E-15 * potencia(distancia, 4)
                            - 1.31669854390918E-11 * potencia(distancia, 3) + 1.75829441445043E-08 * potencia(distancia, 2) + 0.0000316200051506677 * distancia + 0.00137586338365736;
                } else {
                    return -8.100974431E-21 * potencia(distancia, 6) + 2.93954044853116E-16 * potencia(distancia, 5) - 4.41469623946399E-12 * potencia(distancia, 4)
                            + 3.51090127628435E-08 * potencia(distancia, 3) - 0.000155818702194895 * potencia(distancia, 2) + 0.365240077084026 * distancia - 351.019125703552;
                }
            case 6:
                if (isRasante()) {
                    return 1.7938365E-23 * potencia(distancia, 6) - 4.67484283456E-19 * potencia(distancia, 5) + 4.57207715204308E-15 * potencia(distancia, 4)
                            - 1.94937710202999E-11 * potencia(distancia, 3) + 2.52497053144368E-08 * potencia(distancia, 2) + 0.0000907987409051181 * distancia - 0.00481743451314287;
                } else {
                    return -5.069034445E-21 * potencia(distancia, 6) + 2.27951253009912E-16 * potencia(distancia, 5) - 4.24979682484783E-12 * potencia(distancia, 4)
                            + 4.20313780662771E-08 * potencia(distancia, 3) - 0.000232471534217321 * potencia(distancia, 2) + 0.681071513095566 * distancia - 822.983415616696;
                }
            case 7:
                if (isRasante()) {
                    return 1.0407085E-23 * potencia(distancia, 6) - 3.56391184317E-19 * potencia(distancia, 5) + 4.78778559770432E-15 * potencia(distancia, 4)
                            - 3.13244791616823E-11 * potencia(distancia, 3) + 9.61525676225923E-08 * potencia(distancia, 2) - 0.0000297044172477209 * distancia + 0.0233002448953777;
                } else {
                    return -1.359593101E-21 * potencia(distancia, 6) + 7.3543285911398E-17 * potencia(distancia, 5) - 1.64951436382532E-12 * potencia(distancia, 4)
                            + 1.96270244934413E-08 * potencia(distancia, 3) - 0.000130571394628742 * potencia(distancia, 2) + 0.45978451630114 * distancia - 666.06853678843;
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
                    return -2.14093909766E-19 * potencia(distancia, 6) + 2.22159483920989E-15 * potencia(distancia, 5) - 8.69454289728306E-12 * potencia(distancia, 4)
                            + 1.5865675858994E-08 * potencia(distancia, 3) - 0.0000140265346179081 * potencia(distancia, 2) + 0.0159648050511963 * distancia - 0.581009406584255;
                } else {
                    return 3.7977430974072E-17 * potencia(distancia, 6) - 6.53695920772699E-13 * potencia(distancia, 5) + 4.65621666604262E-09 * potencia(distancia, 4)
                            - 0.000017571324320321 * potencia(distancia, 3) + 0.0370600852326162 * potencia(distancia, 2) - 41.4192586591464 * distancia + 19181.1556763577;
                }

            case 2:
                if (isRasante()) {
                    return -7.3090016513E-20 * potencia(distancia, 6) + 9.03264382877838E-16 * potencia(distancia, 5) - 4.23933887405059E-12 * potencia(distancia, 4)
                            + 9.32025215669371E-09 * potencia(distancia, 3) - 9.94848763419091E-06 * potencia(distancia, 2) + 0.014037115985919 * distancia - 0.630508904822097;
                } else {
                    return 1.2930852932296E-17 * potencia(distancia, 6) - 2.59830253379987E-13 * potencia(distancia, 5) + 2.16014541247591E-09 * potencia(distancia, 4)
                            - 9.51405181040096E-06 * potencia(distancia, 3) + 0.023420908856053 * potencia(distancia, 2) - 30.5552464687927 * distancia + 16523.4764339054;
                }
            case 3:
                if (isRasante()) {
                    return -1.9215572388E-20 * potencia(distancia, 6) + 2.52918647023276E-16 * potencia(distancia, 5) - 1.23931022962318E-12 * potencia(distancia, 4)
                            + 2.75689526134208E-09 * potencia(distancia, 3) - 2.96923060050247E-06 * potencia(distancia, 2) + 0.00974225192783251 * distancia - 0.0260687533846067;
                } else {
                    return 5.28641906439E-18 * potencia(distancia, 6) - 1.24692468631737E-13 * potencia(distancia, 5) + 1.21611773939396E-09 * potencia(distancia, 4)
                            - 6.27927491417905E-06 * potencia(distancia, 3) + 0.0181089258827937 * potencia(distancia, 2) - 27.6566486423691 * distancia + 17494.0891676315;
                }
            case 4:
                if (isRasante()) {
                    return -7.570690813E-21 * potencia(distancia, 6) + 1.32727241147196E-16 * potencia(distancia, 5) - 8.68963127705282E-13 * potencia(distancia, 4)
                            + 2.56951854319667E-09 * potencia(distancia, 3) - 3.45908770293946E-06 * potencia(distancia, 2) + 0.00925547072142763 * distancia - 0.140825036964088;
                } else {
                    return 7.40323636882E-19 * potencia(distancia, 6) - 2.21325055718162E-14 * potencia(distancia, 5) + 2.73871826015925E-10 * potencia(distancia, 4)
                            - 0.0000017963542861622 * potencia(distancia, 3) + 0.00658926156589514 * potencia(distancia, 2) - 12.8119691150175 * distancia + 10333.3873372055;
                }
            case 5:
                if (isRasante()) {
                    return -6.25779275E-22 * potencia(distancia, 6) + 1.2874122723424E-17 * potencia(distancia, 5) - 1.06666789974844E-13 * potencia(distancia, 4)
                            + 4.7226872453422E-10 * potencia(distancia, 3) - 1.25906931836006E-06 * potencia(distancia, 2) + 0.00700693209239489 * distancia - 0.0656316980675911;
                } else {
                    return 2.2491496E-22 * potencia(distancia, 6) - 2.41119282186162E-16 * potencia(distancia, 5) + 7.24484674953631E-12 * potencia(distancia, 4)
                            - 8.76219470506794E-08 * potencia(distancia, 3) + 0.000528100335909337 * potencia(distancia, 2) - 1.57696767985254 * distancia + 1883.85986983272;
                }
            case 6:
                if (isRasante()) {
                    return -3.6979666E-23 * potencia(distancia, 6) + 1.530242069191E-18 * potencia(distancia, 5) - 2.74278942711058E-14 * potencia(distancia, 4)
                            + 2.81998638721229E-10 * potencia(distancia, 3) - 1.65056515644135E-06 * potencia(distancia, 2) + 0.0062375140874309 * distancia - 0.0843233989508008;
                } else {
                    return -3.6390491272E-20 * potencia(distancia, 6) + 1.62153474403012E-15 * potencia(distancia, 5) - 3.00238676059472E-11 * potencia(distancia, 4)
                            + 2.95808145267382E-07 * potencia(distancia, 3) - 0.00163654775916314 * potencia(distancia, 2) + 4.82578673471754 * distancia - 5920.25823447941;
                }
            case 7:
                if (isRasante()) {
                    return 9.96056E-25 * potencia(distancia, 6) - 3.58111105312E-19 * potencia(distancia, 5) + 8.87635795305117E-15 * potencia(distancia, 4)
                            - 5.47914457293975E-11 * potencia(distancia, 3) - 2.5395118487562E-07 * potencia(distancia, 2) + 0.00437855378240271 * distancia - 0.112322779507017;
                } else {
                    return -2.654666152E-21 * potencia(distancia, 6) + 1.18625107971069E-16 * potencia(distancia, 5) - 2.1045208360395E-12 * potencia(distancia, 4)
                            + 1.85344328879486E-08 * potencia(distancia, 3) - 0.0000812102854277095 * potencia(distancia, 2) + 0.145126988788775 * distancia - 10.0150946007995;
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
                    return 2.6688991814E-20 * potencia(distancia, 6) - 3.30345261747196E-16 * potencia(distancia, 5) + 1.53392037782012E-12 * potencia(distancia, 4)
                            - 3.22701471316026E-09 * potencia(distancia, 3) + 3.30734764059385E-06 * potencia(distancia, 2) - 0.0106286594209642 * distancia + 0.236373072090662;
                } else {
                    return -1.627604194112E-17 * potencia(distancia, 6) + 2.92918674631651E-13 * potencia(distancia, 5) - 2.18840147653739E-09 * potencia(distancia, 4)
                            + 8.68722241337425E-06 * potencia(distancia, 3) - 0.0193254757657058 * potencia(distancia, 2) + 22.835362973352 * distancia - 11210.396970474;
                }

            case 2:
                if (isRasante()) {
                    return -5.142251684E-21 * potencia(distancia, 6) + 6.8938823201493E-17 * potencia(distancia, 5) - 3.61001527102846E-13 * potencia(distancia, 4)
                            + 9.72169664070697E-10 * potencia(distancia, 3) - 1.16358935421527E-06 * potencia(distancia, 2) - 0.00765873143486287 * distancia - 0.124456921199814;
                } else {
                    return 9.922102527349E-18 * potencia(distancia, 6) - 1.93204346878122E-13 * potencia(distancia, 5) + 1.55165881008482E-09 * potencia(distancia, 4)
                            - 6.57290444912814E-06 * potencia(distancia, 3) + 0.0154713800298389 * potencia(distancia, 2) - 19.1655551962361 * distancia + 9730.95110623277;
                }
            case 3:
                if (isRasante()) {
                    return 4.110673419E-21 * potencia(distancia, 6) - 6.3585226461727E-17 * potencia(distancia, 5) + 3.575491087731E-13 * potencia(distancia, 4)
                            - 8.72178930206441E-10 * potencia(distancia, 3) + 1.07073355593013E-06 * potencia(distancia, 2) - 0.00809481640277454 * distancia + 0.0415451133558236;
                } else {
                    return -9.42634652502E-19 * potencia(distancia, 6) + 2.50296534400767E-14 * potencia(distancia, 5) - 2.73718468368205E-10 * potencia(distancia, 4)
                            + 1.57975433878305E-06 * potencia(distancia, 3) - 0.00507971950013263 * potencia(distancia, 2) + 8.62810051872816 * distancia - 6065.8062503478;
                }
            case 4:
                if (isRasante()) {
                    return -3.5985409E-22 * potencia(distancia, 6) + 2.197442601154E-18 * potencia(distancia, 5) + 1.97439444345797E-14 * potencia(distancia, 4)
                            - 1.5412658291563E-10 * potencia(distancia, 3) + 3.48100017699693E-07 * potencia(distancia, 2) - 0.00699053836154917 * distancia + 0.00559948760928819;
                } else {
                    return -2.8593215555E-19 * potencia(distancia, 6) + 9.37978228976985E-15 * potencia(distancia, 5) - 1.27295960968198E-10 * potencia(distancia, 4)
                            + 9.15181929292856E-07 * potencia(distancia, 3) - 0.00367711930704556 * potencia(distancia, 2) + 7.82366270480438 * distancia - 6903.16955993069;
                }
            case 5:
                if (isRasante()) {
                    return 1.2598338E-23 * potencia(distancia, 6) - 9.43679448864E-19 * potencia(distancia, 5) + 2.12370904545526E-14 * potencia(distancia, 4)
                            - 2.1304844333484E-10 * potencia(distancia, 3) + 9.85940059095025E-07 * potencia(distancia, 2) - 0.00554383414470294 * distancia - 0.0839103584294207;
                } else {
                    return -8.4320605237E-20 * potencia(distancia, 6) + 3.5050581901776E-15 * potencia(distancia, 5) - 6.00119488994188E-11 * potencia(distancia, 4)
                            + 5.42436350748814E-07 * potencia(distancia, 3) - 0.00273249276680921 * potencia(distancia, 2) + 7.27437160964006 * distancia - 8013.97268320498;
                }
            case 6:
                if (isRasante()) {
                    return 1.9252974E-23 * potencia(distancia, 6) - 6.99667033348E-19 * potencia(distancia, 5) + 1.31410957895367E-14 * potencia(distancia, 4)
                            - 1.67311550681656E-10 * potencia(distancia, 3) + 1.23723725956038E-06 * potencia(distancia, 2) - 0.0056739065806255 * distancia + 0.055633901283727;
                } else {
                    return 1.43239659344E-19 * potencia(distancia, 6) - 6.56392918524003E-15 * potencia(distancia, 5) + 1.24723506287591E-10 * potencia(distancia, 4)
                            - 1.25777141988106E-06 * potencia(distancia, 3) + 0.00709965843900403 * potencia(distancia, 2) - 21.2703425102718 * distancia + 26416.5667209821;
                }
            case 7:
                if (isRasante()) {
                    return -2.6028321E-23 * potencia(distancia, 6) + 1.119624790073E-18 * potencia(distancia, 5) - 1.82340003592925E-14 * potencia(distancia, 4)
                            + 1.19421803478561E-10 * potencia(distancia, 3) - 2.4571167367891E-08 * potencia(distancia, 2) - 0.00375629629507729 * distancia - 0.100597899568989;
                } else {
                    return -1.2666445189E-20 * potencia(distancia, 6) + 7.39750643435407E-16 * potencia(distancia, 5) - 1.78912854283723E-11 * potencia(distancia, 4)
                            + 2.29351592649617E-07 * potencia(distancia, 3) - 0.00164345876242353 * potencia(distancia, 2) + 6.2395756085378 * distancia - 9813.99735063798;
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
                    return -9.1201116835E-20 * potencia(distancia, 6) + 1.02816833638117E-15 * potencia(distancia, 5) - 4.58349037389977E-12 * potencia(distancia, 4)
                            + 1.03274307989069E-08 * potencia(distancia, 3) - 0.0000120800784489089 * potencia(distancia, 2) + 0.00690758702904099 * distancia - 1.3951566420587;
                } else {
                    return -4.340277828583E-18 * potencia(distancia, 6) + 7.57211547309851E-14 * potencia(distancia, 5) - 5.46307431604213E-10 * potencia(distancia, 4)
                            + 2.08737983218588E-06 * potencia(distancia, 3) - 0.00445871773415585 * potencia(distancia, 2) + 5.05467858538571 * distancia - 2377.93015632585;
                }

            case 2:
                if (isRasante()) {
                    return -5.142251684E-21 * potencia(distancia, 6) + 6.8938823201493E-17 * potencia(distancia, 5) - 3.61001527102846E-13 * potencia(distancia, 4)
                            + 9.72169664070697E-10 * potencia(distancia, 3) - 1.16358935421527E-06 * potencia(distancia, 2) - 0.00765873143486287 * distancia - 0.124456921199814;
                } else {
                    return 9.922102527349E-18 * potencia(distancia, 6) - 1.93204346878122E-13 * potencia(distancia, 5) + 1.55165881008482E-09 * potencia(distancia, 4)
                            - 6.57290444912814E-06 * potencia(distancia, 3) + 0.0154713800298389 * potencia(distancia, 2) - 19.1655551962361 * distancia + 9730.95110623277;
                }
            case 3:
                if (isRasante()) {
                    return -3.745237021E-21 * potencia(distancia, 6) + 4.9341773531504E-17 * potencia(distancia, 5) - 2.47088340685139E-13 * potencia(distancia, 4)
                            + 6.07058801000543E-10 * potencia(distancia, 3) - 6.2164430320033E-07 * potencia(distancia, 2) + 0.000379466366176473 * distancia - 0.0227734698833046;
                } else {
                    return -1.91506034704E-19 * potencia(distancia, 6) + 4.1567120449844E-15 * potencia(distancia, 5) - 3.74422763724998E-11 * potencia(distancia, 4)
                            + 1.80382290714859E-07 * potencia(distancia, 3) - 0.000494687943428436 * potencia(distancia, 2) + 0.739837905827578 * distancia - 472.389565693155;
                }
            case 4:
                if (isRasante()) {
                    return -1.369711021E-21 * potencia(distancia, 6) + 1.9875255856861E-17 * potencia(distancia, 5) - 9.44518353743152E-14 * potencia(distancia, 4)
                            + 1.73416374745731E-10 * potencia(distancia, 3) - 6.68606059822663E-08 * potencia(distancia, 2) + 0.0000678910573270741 * distancia - 0.010167433757033;
                } else {
                    return -1.032836816413E-18 * potencia(distancia, 6) + 3.05006425204469E-14 * potencia(distancia, 5) - 3.73556961884629E-10 * potencia(distancia, 4)
                            + 2.42898476726086E-06 * potencia(distancia, 3) - 0.00884432843360609 * potencia(distancia, 2) + 17.0988839379524 * distancia - 13708.7414561652;
                }
            case 5:
                if (isRasante()) {
                    return -5.4675754E-22 * potencia(distancia, 6) + 1.1191539232787E-17 * potencia(distancia, 5) - 7.96610271031344E-14 * potencia(distancia, 4)
                            + 2.05720440980611E-10 * potencia(distancia, 3) + 2.14595455710898E-08 * potencia(distancia, 2) + 0.00069221412164211 * distancia - 0.099348109888524;
                } else {
                    return 2.30779665172E-19 * potencia(distancia, 6) - 8.82909855447276E-15 * potencia(distancia, 5) + 1.39680755791262E-10 * potencia(distancia, 4)
                            - 0.000001169761609888 * potencia(distancia, 3) + 0.00547000360738184 * potencia(distancia, 2) - 13.5438105530189 * distancia + 13879.5890210869;
                }
            case 6:
                if (isRasante()) {
                    return -2.80209558E-22 * potencia(distancia, 6) + 7.39696264654E-18 * potencia(distancia, 5) - 7.0775233629533E-14 * potencia(distancia, 4)
                            + 2.64733885563305E-10 * potencia(distancia, 3) - 1.92847309371524E-08 * potencia(distancia, 2) + 0.000199225584424312 * distancia - 0.0450637357535015;
                } else {
                    return -6.1625355335E-20 * potencia(distancia, 6) + 2.87026501157835E-15 * potencia(distancia, 5) - 5.54931812199034E-11 * potencia(distancia, 4)
                            + 5.69813743689317E-07 * potencia(distancia, 3) - 0.00327597932513328 * potencia(distancia, 2) + 9.99668802912604 * distancia - 12638.5830471061;
                }
            case 7:
                if (isRasante()) {
                    return 4.296155E-24 * potencia(distancia, 6) - 3.85311286387E-19 * potencia(distancia, 5) + 8.60513561575047E-15 * potencia(distancia, 4)
                            - 6.59018155536941E-11 * potencia(distancia, 3) - 5.37142032182813E-09 * potencia(distancia, 2) - 0.0000548136511228343 * distancia - 0.0052526393024355;
                } else {
                    return 8.27581471E-22 * potencia(distancia, 6) + 2.7925500601805E-17 * potencia(distancia, 5) - 2.34598640561848E-12 * potencia(distancia, 4)
                            + 4.94864553199063E-08 * potencia(distancia, 3) - 0.000480761614687398 * potencia(distancia, 2) + 2.25860358704156 * distancia - 4174.45151982222;
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
                    return 2.7961299025E-20 * potencia(distancia, 6) - 2.46753323872925E-16 * potencia(distancia, 5) + 7.83835764941901E-13 * potencia(distancia, 4)
                            - 1.11743615482048E-09 * potencia(distancia, 3) + 5.64726975979012E-07 * potencia(distancia, 2) - 0.0000732465886828005 * distancia - 0.00342815039581265;
                } else {
                    return 4.340277848475E-18 * potencia(distancia, 6) - 7.79246807200989E-14 * potencia(distancia, 5) + 5.82164805916179E-10 * potencia(distancia, 4)
                            - 2.31692893855872E-06 * potencia(distancia, 3) + 0.00518408415919501 * potencia(distancia, 2) - 6.19105489079603 * distancia + 3087.8047593629;
                }

            case 2:
                if (isRasante()) {
                    return 7.108120927E-21 * potencia(distancia, 6) - 8.0172769271604E-17 * potencia(distancia, 5) + 3.39824073287448E-13 * potencia(distancia, 4)
                            - 7.25207260926428E-10 * potencia(distancia, 3) + 7.51113582792671E-07 * potencia(distancia, 2) - 0.000545181819537285 * distancia + 0.0712286724282508;
                } else {
                    return -8.002381078999E-18 * potencia(distancia, 6) + 1.60595866919467E-13 * potencia(distancia, 5) - 1.33441806676912E-09 * potencia(distancia, 4)
                            + 0.0000058748150395894 * potencia(distancia, 3) - 0.0144474597030244 * potencia(distancia, 2) + 18.8052174355468 * distancia - 10113.4220621544;
                }
            case 3:
                if (isRasante()) {
                    return -2.614749761E-21 * potencia(distancia, 6) + 3.7048463780585E-17 * potencia(distancia, 5) - 1.94765053994013E-13 * potencia(distancia, 4)
                            + 4.54618720835789E-10 * potencia(distancia, 3) - 5.57065874054463E-07 * potencia(distancia, 2) + 0.0000852557722197389 * distancia + 0.0038358032566066;
                } else {
                    return 8.59135165652E-19 * potencia(distancia, 6) - 2.13198881411002E-14 * potencia(distancia, 5) + 2.18576215488289E-10 * potencia(distancia, 4)
                            - 1.18604118692663E-06 * potencia(distancia, 3) + 0.00359692671688108 * potencia(distancia, 2) - 5.79035656462472 * distancia + 3870.99382511401;
                }
            case 4:
                if (isRasante()) {
                    return -4.73368014E-22 * potencia(distancia, 6) + 1.0234493508921E-17 * potencia(distancia, 5) - 7.98577683590006E-14 * potencia(distancia, 4)
                            + 2.53889648576242E-10 * potencia(distancia, 3) - 3.5416915246389E-07 * potencia(distancia, 2) + 0.0000594808851559492 * distancia - 0.0194099295808883;
                } else {
                    return -6.6323194143E-20 * potencia(distancia, 6) + 2.09841553779016E-15 * potencia(distancia, 5) - 2.73782059590254E-11 * potencia(distancia, 4)
                            + 1.87902948274605E-07 * potencia(distancia, 3) - 0.000712658664410727 * potencia(distancia, 2) + 1.40924833823011 * distancia - 1131.42729081866;
                }
            case 5:
                if (isRasante()) {
                    return 3.02712021E-22 * potencia(distancia, 6) - 7.129415329938E-18 * potencia(distancia, 5) + 5.86617274942531E-14 * potencia(distancia, 4)
                            - 1.96276728231553E-10 * potencia(distancia, 3) + 1.56127992360922E-07 * potencia(distancia, 2) - 0.000325265769035354 * distancia + 0.0286697584733702;
                } else {
                    return 9.9033150025E-20 * potencia(distancia, 6) - 3.47861012686114E-15 * potencia(distancia, 5) + 5.03621472116712E-11 * potencia(distancia, 4)
                            - 3.84626929982345E-07 * potencia(distancia, 3) + 0.00163435510415623 * potencia(distancia, 2) - 3.66499497517007 * distancia + 3386.73649633844;
                }
            case 6:
                if (isRasante()) {
                    return -5.1601709E-23 * potencia(distancia, 6) + 1.474684989633E-18 * potencia(distancia, 5) - 1.88662006782769E-14 * potencia(distancia, 4)
                            + 1.55898915786101E-10 * potencia(distancia, 3) - 8.53827364574733E-07 * potencia(distancia, 2) + 0.000467231763266795 * distancia - 0.0653526178684842;
                } else {
                    return -4.3177423885E-20 * potencia(distancia, 6) + 2.06406237952387E-15 * potencia(distancia, 5) - 4.09151916631596E-11 * potencia(distancia, 4)
                            + 4.3037600498918E-07 * potencia(distancia, 3) - 0.00253328832990146 * potencia(distancia, 2) + 7.9098505607622 * distancia - 10241.3014931331;
                }
            case 7:
                if (isRasante()) {
                    return 4.296155E-24 * potencia(distancia, 6) - 3.85311286387E-19 * potencia(distancia, 5) + 8.60513561575047E-15 * potencia(distancia, 4)
                            - 6.59018155536941E-11 * potencia(distancia, 3) - 5.37142032182813E-09 * potencia(distancia, 2) - 0.0000548136511228343 * distancia - 0.0052526393024355;
                } else {
                    return 8.27581471E-22 * potencia(distancia, 6) + 2.7925500601805E-17 * potencia(distancia, 5) - 2.34598640561848E-12 * potencia(distancia, 4)
                            + 4.94864553199063E-08 * potencia(distancia, 3) - 0.000480761614687398 * potencia(distancia, 2) + 2.25860358704156 * distancia - 4174.45151982222;
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
                    return -1.05636871145E-19 * potencia(distancia, 6) + 1.12434984932112E-15 * potencia(distancia, 5) - 4.6518498845249E-12 * potencia(distancia, 4)
                            + 9.48653503710392E-09 * potencia(distancia, 3) - 9.84491777685538E-06 * potencia(distancia, 2) + 0.0046687386623394 * distancia - 0.820633696797744;
                } else {
                    return 9.765625086972E-18 * potencia(distancia, 6) - 1.644130624137E-13 * potencia(distancia, 5) + 1.14603366479928E-09 * potencia(distancia, 4)
                            - 0.0000042339753114821 * potencia(distancia, 3) + 0.00874538000685433 * potencia(distancia, 2) - 9.57763648031039 * distancia + 4345.52227995091;
                }

            case 2:
                if (isRasante()) {
                    return -3.5302871426E-20 * potencia(distancia, 6) + 4.29190825159522E-16 * potencia(distancia, 5) - 1.94760719956971E-12 * potencia(distancia, 4)
                            + 4.07414630581135E-09 * potencia(distancia, 3) - 3.95127022441402E-06 * potencia(distancia, 2) + 0.00165217735138245 * distancia - 0.146285093884311;
                } else {
                    return 1.0488294620456E-17 * potencia(distancia, 6) - 2.10551175284777E-13 * potencia(distancia, 5) + 1.75177774561347E-09 * potencia(distancia, 4)
                            - 7.73103599040253E-06 * potencia(distancia, 3) + 0.0190860597274995 * potencia(distancia, 2) - 24.9897575424168 * potencia(distancia, 1) + 13556.1385626359;
                }
            case 3:
                if (isRasante()) {
                    return 1.009357859E-21 * potencia(distancia, 6) - 1.0672322159027E-17 * potencia(distancia, 5) + 2.85714775576641E-14 * potencia(distancia, 4)
                            + 1.66354075959522E-11 * potencia(distancia, 3) - 1.23408299270572E-07 * potencia(distancia, 2) + 0.000203894904207136 * distancia - 0.0190794984069385;
                } else {
                    return -1.65222328626E-19 * potencia(distancia, 6) + 4.45851956888184E-15 * potencia(distancia, 5) - 4.84418344348548E-11 * potencia(distancia, 4)
                            + 2.73926304826727E-07 * potencia(distancia, 3) - 0.000855906878689268 * potencia(distancia, 2) + 1.40720086809686 * distancia - 954.32920650988;
                }
            case 4:
                if (isRasante()) {
                    return -1.845384261E-21 * potencia(distancia, 6) + 3.2183327435026E-17 * potencia(distancia, 5) - 2.0462827833792E-13 * potencia(distancia, 4)
                            + 6.02136037252256E-10 * potencia(distancia, 3) - 9.50786205183851E-07 * potencia(distancia, 2) + 0.000482619033441267 * distancia - 0.0768499823602724;
                } else {
                    return -3.16023188733E-19 * potencia(distancia, 6) + 9.06563449671302E-15 * potencia(distancia, 5) - 1.07517006165618E-10 * potencia(distancia, 4)
                            + 6.75346190157736E-07 * potencia(distancia, 3) - 0.00237147714698801 * potencia(distancia, 2) + 4.41659957689402 * distancia - 3411.85816739107;
                }
            case 5:
                if (isRasante()) {
                    return -1.586336608E-21 * potencia(distancia, 6) + 3.4172499146986E-17 * potencia(distancia, 5) - 2.62161600165864E-13 * potencia(distancia, 4)
                            + 7.8788247292723E-10 * potencia(distancia, 3) - 6.43045469997383E-07 * potencia(distancia, 2) + 0.00222522861552932 * distancia - 0.312936357022409;
                } else {
                    return -1.76931024545E-19 * potencia(distancia, 6) + 6.48786868533096E-15 * potencia(distancia, 5) - 9.87989788249357E-11 * potencia(distancia, 4)
                            + 7.99844999487121E-07 * potencia(distancia, 3) - 0.00363008940943968 * potencia(distancia, 2) + 8.75518098038593 * distancia - 8759.69004941094;
                }
            case 6:
                if (isRasante()) {
                    return -4.739347E-22 * potencia(distancia, 6) + 1.3570910401644E-17 * potencia(distancia, 5) - 1.39129857159984E-13 * potencia(distancia, 4)
                            + 5.01405189910027E-10 * potencia(distancia, 3) + 4.44553544726167E-07 * potencia(distancia, 2) - 0.000630214832398224 * distancia - 0.00556637410772964;
                } else {
                    return -4.2473279648E-20 * potencia(distancia, 6) + 2.05856264289128E-15 * potencia(distancia, 5) - 4.14386948959484E-11 * potencia(distancia, 4)
                            + 4.43038313286215E-07 * potencia(distancia, 3) - 0.00265088677825138 * potencia(distancia, 2) + 8.41311133474383 * distancia - 11045.0471246335;
                }
            case 7:
                if (isRasante()) {
                    return -8.2474788E-23 * potencia(distancia, 6) + 4.765780390806E-18 * potencia(distancia, 5) - 9.81830493261772E-14 * potencia(distancia, 4)
                            + 8.59898015363316E-10 * potencia(distancia, 3) - 2.70875518226467E-06 * potencia(distancia, 2) + 0.00259833692712415 * distancia - 0.907995363736158;
                } else {
                    return -3.5575824787E-20 * potencia(distancia, 6) + 2.03802237396074E-15 * potencia(distancia, 5) - 4.83723139377639E-11 * potencia(distancia, 4)
                            + 6.08627614770627E-07 * potencia(distancia, 3) - 0.00427991222773891 * potencia(distancia, 2) + 15.9462228526186 * distancia - 24576.4313146169;
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
                    return 1.01821051568E-19 * potencia(distancia, 6) - 1.12621919632451E-15 * potencia(distancia, 5) + 4.91772924029236E-12 * potencia(distancia, 4)
                            - 1.07535630331748E-08 * potencia(distancia, 3) + 0.0000121527081508106 * potencia(distancia, 2) - 0.00646643377018166 * distancia + 1.33134185481535;
                } else {
                    return 1.5190972425535E-17 * potencia(distancia, 6) - 2.68930292004497E-13 * potencia(distancia, 5) + 1.97001872215879E-09 * potencia(distancia, 4)
                            - 7.64242980360993E-06 * potencia(distancia, 3) + 0.0165586489323074 * potencia(distancia, 2) - 18.9988896607319 * distancia + 9019.44457552191;
                }

            case 2:
                if (isRasante()) {
                    return 1.4941092076E-20 * potencia(distancia, 6) - 1.62734649499206E-16 * potencia(distancia, 5) + 6.18428865191178E-13 * potencia(distancia, 4)
                            - 9.48599271720987E-10 * potencia(distancia, 3) + 4.76901283441999E-07 * potencia(distancia, 2) + 0.0000022397536197584 * distancia - 0.121184170327802;
                } else {
                    return -8.639806421849E-18 * potencia(distancia, 6) + 1.73377577452464E-13 * potencia(distancia, 5) - 1.44445721446579E-09 * potencia(distancia, 4)
                            + 6.39467856352405E-06 * potencia(distancia, 3) - 0.0158633826312655 * potencia(distancia, 2) + 20.9038485635391 * distancia - 11428.0586719657;
                }
            case 3:
                if (isRasante()) {
                    return -1.00010803E-21 * potencia(distancia, 6) + 6.57995508981E-18 * potencia(distancia, 5) + 1.15712152218398E-14 * potencia(distancia, 4)
                            - 1.46694009976761E-10 * potencia(distancia, 3) + 2.69917031595415E-07 * potencia(distancia, 2) - 0.000211251275792357 * distancia - 0.0224078157620511;
                } else {
                    return -3.107970776645E-18 * potencia(distancia, 6) + 7.43365811112244E-14 * potencia(distancia, 5) - 7.37698335245465E-10 * potencia(distancia, 4)
                            + 3.88654317072911E-06 * potencia(distancia, 3) - 0.0114610963038882 * potencia(distancia, 2) + 17.9309853363527 * distancia - 11623.6856927986;
                }
            case 4:
                if (isRasante()) {
                    return -5.7515575E-23 * potencia(distancia, 6) + 6.2277807934E-20 * potencia(distancia, 5) + 6.45543173648796E-15 * potencia(distancia, 4)
                            - 6.03540849431925E-11 * potencia(distancia, 3) + 2.54107803243642E-07 * potencia(distancia, 2) - 0.0000796044836466336 * distancia - 0.0515137720049097;
                } else {
                    return -8.07999196715E-19 * potencia(distancia, 6) + 2.40086467726411E-14 * potencia(distancia, 5) - 2.95470200208356E-10 * potencia(distancia, 4)
                            + 1.92682827544947E-06 * potencia(distancia, 3) - 0.00701953716981706 * potencia(distancia, 2) + 13.5422544961556 * distancia - 10806.2629458903;
                }
            case 5:
                if (isRasante()) {
                    return 1.573087985E-21 * potencia(distancia, 6) - 3.5870160766335E-17 * potencia(distancia, 5) + 2.95137731582851E-13 * potencia(distancia, 4)
                            - 1.02332085991078E-09 * potencia(distancia, 3) + 1.34389713622729E-06 * potencia(distancia, 2) - 0.00136250737083543 * distancia + 0.0745093566379182;
                } else {
                    return 1.40544252039E-19 * potencia(distancia, 6) - 5.09297737801384E-15 * potencia(distancia, 5) + 7.64827562233419E-11 * potencia(distancia, 4)
                            - 6.09479242472121E-07 * potencia(distancia, 3) + 0.00271864803980729 * potencia(distancia, 2) - 6.43605393367396 * distancia + 6316.38424605962;
                }
            case 6:
                if (isRasante()) {
                    return 1.03765067E-22 * potencia(distancia, 6) - 3.13955080954E-18 * potencia(distancia, 5) + 2.62451368720987E-14 * potencia(distancia, 4)
                            + 6.76506586752383E-11 * potencia(distancia, 3) - 1.67721055907566E-06 * potencia(distancia, 2) + 0.00145599208576641 * distancia - 0.241988728004799;
                } else {
                    return -2.89132939719E-19 * potencia(distancia, 6) + 1.33026473415618E-14 * potencia(distancia, 5) - 2.53794869809511E-10 * potencia(distancia, 4)
                            + 0.0000025700377252837 * potencia(distancia, 3) - 0.0145697399667202 * potencia(distancia, 2) + 43.8432956682046 * distancia - 54730.0617591178;
                }
            case 7:
                if (isRasante()) {
                    return 9.215507E-23 * potencia(distancia, 6) - 5.008087849342E-18 * potencia(distancia, 5) + 9.68796123315203E-14 * potencia(distancia, 4)
                            - 7.94797296019783E-10 * potencia(distancia, 3) + 2.25673280738673E-06 * potencia(distancia, 2) - 0.00175587236153649 * distancia + 0.329568028646805;
                } else {
                    return -7.1493219582E-20 * potencia(distancia, 6) + 3.91705437018118E-15 * potencia(distancia, 5) - 8.89488581942876E-11 * potencia(distancia, 4)
                            + 1.07170665401601E-06 * potencia(distancia, 3) - 0.00722722498985645 * potencia(distancia, 2) + 25.867661576493 * distancia - 38409.8128606935;
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
                    return -6.0953907094E-20 * potencia(distancia, 6) + 7.08795256601464E-16 * potencia(distancia, 5) - 3.14095572634654E-12 * potencia(distancia, 4)
                            + 6.54713487061807E-09 * potencia(distancia, 3) - 6.82334000517106E-06 * potencia(distancia, 2) + 0.00303467159252433 * distancia - 0.480996991535568;
                } else {
                    return 4.340277823029E-18 * potencia(distancia, 6) - 7.4919872583308E-14 * potencia(distancia, 5) + 5.34688841159009E-10 * potencia(distancia, 4)
                            - 2.01873181083323E-06 * potencia(distancia, 3) + 0.00425107932510462 * potencia(distancia, 2) - 4.73414780962973 * distancia + 2176.34448893488;
                }

            case 2:
                if (isRasante()) {
                    return -5.836378705E-21 * potencia(distancia, 6) + 8.875625459161E-17 * potencia(distancia, 5) - 4.79645007270375E-13 * potencia(distancia, 4)
                            + 1.09352786609956E-09 * potencia(distancia, 3) - 0.0000013118469256169 * potencia(distancia, 2) + 0.000447839184961916 * distancia - 0.0421669933746215;
                } else {
                    return 6.48635044514E-18 * potencia(distancia, 6) - 1.29730532548628E-13 * potencia(distancia, 5) + 1.07478691964984E-09 * potencia(distancia, 4)
                            - 4.72107043240083E-06 * potencia(distancia, 3) + 0.01159664622135 * potencia(distancia, 2) - 15.1064887189019 * distancia + 8152.63642289077;
                }
            case 3:
                if (isRasante()) {
                    return -3.170801389E-21 * potencia(distancia, 6) + 5.1994489948929E-17 * potencia(distancia, 5) - 3.09225567430735E-13 * potencia(distancia, 4)
                            + 7.8294749505871E-10 * potencia(distancia, 3) - 0.0000010599774688258 * potencia(distancia, 2) + 0.000289258286073846 * distancia - 0.00661611568557419;
                } else {
                    return 1.572728580264E-18 * potencia(distancia, 6) - 3.73319489506205E-14 * potencia(distancia, 5) + 3.67196475910354E-10 * potencia(distancia, 4)
                            - 1.91531089808437E-06 * potencia(distancia, 3) + 0.00558705248682821 * potencia(distancia, 2) - 8.64338295334997 * distancia + 5537.3006741857;
                }
            case 4:
                if (isRasante()) {
                    return -2.847000792E-21 * potencia(distancia, 6) + 5.8722279477572E-17 * potencia(distancia, 5) - 4.54886712889053E-13 * potencia(distancia, 4)
                            + 1.60859136423523E-09 * potencia(distancia, 3) - 0.0000028329938449011 * potencia(distancia, 2) + 0.0017054259704186 * distancia - 0.377255319686415;
                } else {
                    return 4.71620052393E-19 * potencia(distancia, 6) - 1.41820820841774E-14 * potencia(distancia, 5) + 1.76636188919368E-10 * potencia(distancia, 4)
                            - 1.16607142162963E-06 * potencia(distancia, 3) + 0.0043028697757714 * potencia(distancia, 2) - 8.41704631954748 * distancia + 6814.42352956259;
                }
            case 5:
                if (isRasante()) {
                    return -4.66790476E-22 * potencia(distancia, 6) + 1.1299871296331E-17 * potencia(distancia, 5) - 1.01699160085101E-13 * potencia(distancia, 4)
                            + 4.11209117565136E-10 * potencia(distancia, 3) - 9.82505340668682E-07 * potencia(distancia, 2) + 0.000401024827112906 * distancia - 0.045066883236359;
                } else {
                    return -9.2110577614E-20 * potencia(distancia, 6) + 3.52823610433535E-15 * potencia(distancia, 5) - 5.61328056150231E-11 * potencia(distancia, 4)
                            + 4.74892293147065E-07 * potencia(distancia, 3) - 0.0022533644173602 * potencia(distancia, 2) + 5.68271134184422 * distancia - 5956.12278559764;
                }
            case 6:
                if (isRasante()) {
                    return -4.2162548E-23 * potencia(distancia, 6) + 2.358610481599E-18 * potencia(distancia, 5) - 4.064502491283E-14 * potencia(distancia, 4)
                            + 2.82003431863838E-10 * potencia(distancia, 3) - 9.76063753793666E-07 * potencia(distancia, 2) - 0.000164685374386409 * distancia + 0.00100504110741895;
                } else {
                    return 2.21773642947E-19 * potencia(distancia, 6) - 1.00733751934387E-14 * potencia(distancia, 5) + 1.89693096778645E-10 * potencia(distancia, 4)
                            - 1.89563333246929E-06 * potencia(distancia, 3) + 0.0106030944857225 * potencia(distancia, 2) - 31.4809912156848 * distancia + 38753.7295543208;
                }
            case 7:
                if (isRasante()) {
                    return 1.7049483E-23 * potencia(distancia, 6) + 5.41573182283E-19 * potencia(distancia, 5) - 3.12607134900613E-14 * potencia(distancia, 4)
                            + 4.16471011030607E-10 * potencia(distancia, 3) - 2.23218695408589E-06 * potencia(distancia, 2) + 0.00150527435846888 * distancia - 0.464605226636757;
                } else {
                    return 4.9664149332E-20 * potencia(distancia, 6) - 2.63306497238167E-15 * potencia(distancia, 5) + 5.77467753396523E-11 * potencia(distancia, 4)
                            - 6.70468816391818E-07 * potencia(distancia, 3) + 0.00434588667404641 * potencia(distancia, 2) - 14.913006458843 * distancia + 21144.0116707041;
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
                    return 6.081124062E-21 * potencia(distancia, 6) - 6.3810132864059E-17 * potencia(distancia, 5) + 1.83602694349367E-13 * potencia(distancia, 4)
                            + 1.2350372445629E-10 * potencia(distancia, 3) - 8.0877563966595E-07 * potencia(distancia, 2) + 0.00101692906853281 * distancia - 0.250516327112726;
                } else {
                    return 2.170138923934E-18 * potencia(distancia, 6) - 3.93629813775194E-14 * potencia(distancia, 5) + 2.94938572758147E-10 * potencia(distancia, 4)
                            - 1.16833298584509E-06 * potencia(distancia, 3) + 0.00257997349623105 * potencia(distancia, 2) - 3.00878241205534 * distancia + 1448.51907479762;
                }

            case 2:
                if (isRasante()) {
                    return 8.722370785E-21 * potencia(distancia, 6) - 1.23549714707477E-16 * potencia(distancia, 5) + 6.37598195273445E-13 * potencia(distancia, 4)
                            - 1.42783929220396E-09 * potencia(distancia, 3) + 1.64740291041493E-06 * potencia(distancia, 2) - 0.000591776554682302 * distancia + 0.0612218024217491;
                } else {
                    return -4.567667044794E-18 * potencia(distancia, 6) + 9.11817642193868E-14 * potencia(distancia, 5) - 7.54555147072651E-10 * potencia(distancia, 4)
                            + 3.31320147086365E-06 * potencia(distancia, 3) - 0.0081415631608719 * potencia(distancia, 2) + 10.617917466316 * distancia - 5740.3152793119;
                }
            case 3:
                if (isRasante()) {
                    return -4.639271064E-21 * potencia(distancia, 6) + 5.4351453292391E-17 * potencia(distancia, 5) - 2.34137936683518E-13 * potencia(distancia, 4)
                            + 5.04357312126414E-10 * potencia(distancia, 3) - 3.32276487269301E-07 * potencia(distancia, 2) + 0.000261452228244252 * distancia - 0.0257609347554535;
                } else {
                    return -1.965090282587E-18 * potencia(distancia, 6) + 4.61752718044533E-14 * potencia(distancia, 5) - 4.50041258402935E-10 * potencia(distancia, 4)
                            + 2.32837368258663E-06 * potencia(distancia, 3) - 0.00674351535184464 * potencia(distancia, 2) + 10.3674575681439 * distancia - 6606.42216450388;
                }
            case 4:
                if (isRasante()) {
                    return -4.75530393E-22 * potencia(distancia, 6) + 1.9313384782E-19 * potencia(distancia, 5) + 5.18354952455339E-14 * potencia(distancia, 4)
                            - 2.63213569893571E-10 * potencia(distancia, 3) + 5.60237573918782E-07 * potencia(distancia, 2) + 0.000103145163994291 * distancia - 0.1332763180929;
                } else {
                    return -2.25236945294E-19 * potencia(distancia, 6) + 6.34060022950515E-15 * potencia(distancia, 5) - 7.37352296108557E-11 * potencia(distancia, 4)
                            + 4.53005548754197E-07 * potencia(distancia, 3) - 0.00154965455073841 * potencia(distancia, 2) + 2.7991253749121 * distancia - 2080.19125219736;
                }
            case 5:
                if (isRasante()) {
                    return -3.89831877E-22 * potencia(distancia, 6) + 6.971775801421E-18 * potencia(distancia, 5) - 4.46135923684654E-14 * potencia(distancia, 4)
                            + 1.32979632622328E-10 * potencia(distancia, 3) + 5.09883415211743E-08 * potencia(distancia, 2) + 0.000227111246488221 * distancia - 0.0474167712709459;
                } else {
                    return -3.43512349313E-19 * potencia(distancia, 6) + 1.27645664545464E-14 * potencia(distancia, 5) - 1.96558046689272E-10 * potencia(distancia, 4)
                            + 1.60534397915575E-06 * potencia(distancia, 3) - 0.00733430184852358 * potencia(distancia, 2) + 17.775745228153 * distancia - 17850.4586501482;
                }
            case 6:
                if (isRasante()) {
                    return -1.32339899E-22 * potencia(distancia, 6) + 2.161447372116E-18 * potencia(distancia, 5) - 4.53383637033016E-15 * potencia(distancia, 4)
                            - 6.20834306815261E-11 * potencia(distancia, 3) + 4.56562831013807E-07 * potencia(distancia, 2) + 0.000643086928846515 * distancia - 0.132862576927437;
                } else {
                    return -1.98958566525E-19 * potencia(distancia, 6) + 8.90089074337912E-15 * potencia(distancia, 5) - 1.65120505410121E-10 * potencia(distancia, 4)
                            + 1.62610306599318E-06 * potencia(distancia, 3) - 0.0089681875321175 * potencia(distancia, 2) + 26.274180824317 * distancia - 31944.4760949934;
                }
            case 7:
                if (isRasante()) {
                    return -1.07280341E-22 * potencia(distancia, 6) + 2.130373457882E-18 * potencia(distancia, 5) + 1.34154610620704E-15 * potencia(distancia, 4)
                            - 2.49533542689608E-10 * potencia(distancia, 3) + 1.71869034526916E-06 * potencia(distancia, 2) - 0.000749168970254941 * distancia + 0.108380257404418;
                } else {
                    return -9.174660691E-21 * potencia(distancia, 6) + 3.85450404535102E-16 * potencia(distancia, 5) - 6.12577110017486E-12 * potencia(distancia, 4)
                            + 4.27082134413716E-08 * potencia(distancia, 3) - 0.0000832182615606786 * potencia(distancia, 2) - 0.408840897623881 * distancia + 1629.51504083735;
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
                    return -8.9630590893E-20 * potencia(distancia, 6) + 1.00209365357731E-15 * potencia(distancia, 5) - 4.52453467194758E-12 * potencia(distancia, 4)
                            + 1.07136454789194E-08 * potencia(distancia, 3) - 0.0000121447248818125 * potencia(distancia, 2) - 0.0114452895655124 * distancia - 0.257778450085657;
                } else {
                    return -1.0850694645723E-17 * potencia(distancia, 6) + 1.95813304740772E-13 * potencia(distancia, 5) - 1.46668004601611E-09 * potencia(distancia, 4)
                            + 5.83611059713365E-06 * potencia(distancia, 3) - 0.0130109397599602 * potencia(distancia, 2) + 15.3930402829346 * distancia - 7566.03186152193;
                }

            case 2:
                if (isRasante()) {
                    return -1.8237254256E-20 * potencia(distancia, 6) + 2.65489873149045E-16 * potencia(distancia, 5) - 1.5622916615685E-12 * potencia(distancia, 4)
                            + 4.77036055469606E-09 * potencia(distancia, 3) - 6.26149780128906E-06 * potencia(distancia, 2) - 0.0129574462915003 * distancia - 0.130675437582035;
                } else {
                    return 1.0190547779101E-17 * potencia(distancia, 6) - 1.98303360142231E-13 * potencia(distancia, 5) + 1.58862393473025E-09 * potencia(distancia, 4)
                            - 6.69604184386308E-06 * potencia(distancia, 3) + 0.0156327942802779 * potencia(distancia, 2) - 19.1359580521373 * distancia + 9551.29315402981;
                }
            case 3:
                if (isRasante()) {
                    return 1.1058780702E-20 * potencia(distancia, 6) - 1.52494455363909E-16 * potencia(distancia, 5) + 7.33372719178601E-13 * potencia(distancia, 4)
                            - 1.29683777410315E-09 * potencia(distancia, 3) + 0.0000011914512454414 * potencia(distancia, 2) - 0.0154510094284888 * distancia + 0.0234150329051772;
                } else {
                    return 3.034143960978E-18 * potencia(distancia, 6) - 7.01590603734483E-14 * potencia(distancia, 5) + 6.69216987617653E-10 * potencia(distancia, 4)
                            - 3.36732461262245E-06 * potencia(distancia, 3) + 0.00941601676055369 * potencia(distancia, 2) - 13.8678578541494 * distancia + 8377.43123230302;
                }
            case 4:
                if (isRasante()) {
                    return 1.049201935E-20 * potencia(distancia, 6) - 2.22951789696101E-16 * potencia(distancia, 5) + 1.78314641775873E-12 * potencia(distancia, 4)
                            - 6.51364296954313E-09 * potencia(distancia, 3) + 0.0000113696865020192 * potencia(distancia, 2) - 0.0206757691380092 * distancia + 1.75852223552936;
                } else {
                    return -1.721953316126E-18 * potencia(distancia, 6) + 5.23534548018738E-14 * potencia(distancia, 5) - 6.60472406009606E-10 * potencia(distancia, 4)
                            + 4.42414475860924E-06 * potencia(distancia, 3) - 0.016589766637547 * potencia(distancia, 2) + 32.9956292336125 * distancia - 27196.091524043;
                }
            case 5:
                if (isRasante()) {
                    return 6.6785071E-22 * potencia(distancia, 6) - 1.8019078549941E-17 * potencia(distancia, 5) + 1.89185488599215E-13 * potencia(distancia, 4)
                            - 9.88699381046509E-10 * potencia(distancia, 3) + 3.18263057896839E-06 * potencia(distancia, 2) - 0.0111429469849327 * distancia + 0.0161586228132364;
                } else {
                    return -1.117196429175E-18 * potencia(distancia, 6) + 4.19555744106025E-14 * potencia(distancia, 5) - 6.52680114326306E-10 * potencia(distancia, 4)
                            + 5.38344265315921E-06 * potencia(distancia, 3) - 0.0248306595152425 * potencia(distancia, 2) + 60.7192603247514 * distancia - 61507.386868989;
                }
            case 6:
                if (isRasante()) {
                    return 5.36237886E-22 * potencia(distancia, 6) - 1.6379429555978E-17 * potencia(distancia, 5) + 1.98564417286183E-13 * potencia(distancia, 4)
                            - 1.24655056612524E-09 * potencia(distancia, 3) + 4.91996043863047E-06 * potencia(distancia, 2) - 0.0114801940132949 * distancia + 0.0937966987848995;
                } else {
                    return -2.5649587825E-19 * potencia(distancia, 6) + 1.16593963555712E-14 * potencia(distancia, 5) - 2.19787949531124E-10 * potencia(distancia, 4)
                            + 2.19950287724797E-06 * potencia(distancia, 3) - 0.0123258892134149 * potencia(distancia, 2) + 36.6785093759341 * distancia - 45270.9817305016;
                }
            case 7:
                if (isRasante()) {
                    return -4.67542847E-22 * potencia(distancia, 6) + 1.3746659542465E-17 * potencia(distancia, 5) - 1.31939749703994E-13 * potencia(distancia, 4)
                            + 3.32100354391077E-10 * potencia(distancia, 3) + 2.02175868856878E-06 * potencia(distancia, 2) - 0.0105197366845325 * distancia + 0.331571630762919;
                } else {
                    return 2.6714545835E-20 * potencia(distancia, 6) - 1.51810108054788E-15 * potencia(distancia, 5) + 3.58480005824025E-11 * potencia(distancia, 4)
                            - 4.50297927372338E-07 * potencia(distancia, 3) + 0.00317415661821213 * potencia(distancia, 2) - 11.9087216992785 * distancia + 18616.2947195205;
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
                    return -1.59923113492E-19 * potencia(distancia, 6) + 1.75619180960423E-15 * potencia(distancia, 5) - 7.05561333590627E-12 * potencia(distancia, 4)
                            + 1.22885193575383E-08 * potencia(distancia, 3) - 9.79304295216176E-06 * potencia(distancia, 2) + 0.0201205811844931 * distancia - 0.794580724472172;
                } else {
                    return 3.2552083769148E-17 * potencia(distancia, 6) - 5.71414270366497E-13 * potencia(distancia, 5) + 4.15464749008483E-09 * potencia(distancia, 4)
                            - 0.0000160165266486465 * potencia(distancia, 3) + 0.0345294355352744 * potencia(distancia, 2) - 39.454546969753 * distancia + 18681.2534370654;
                }

            case 2:
                if (isRasante()) {
                    return -2.9880266245E-20 * potencia(distancia, 6) + 3.36548099989563E-16 * potencia(distancia, 5) - 1.40173045401888E-12 * potencia(distancia, 4)
                            + 2.44178574477906E-09 * potencia(distancia, 3) - 2.60656747647373E-06 * potencia(distancia, 2) + 0.0186460922730143 * distancia - 0.801227101211461;
                } else {
                    return -2.313081336842E-18 * potencia(distancia, 6) + 4.58066084591418E-14 * potencia(distancia, 5) - 3.84785968610472E-10 * potencia(distancia, 4)
                            + 1.75133132320971E-06 * potencia(distancia, 3) - 0.00453822464583231 * potencia(distancia, 2) + 6.33330858960339 * distancia - 3680.68796649107;
                }
            case 3:
                if (isRasante()) {
                    return -1.0827125782E-20 * potencia(distancia, 6) + 1.14289427408514E-16 * potencia(distancia, 5) - 3.29169059811174E-13 * potencia(distancia, 4)
                            - 1.97834765713542E-10 * potencia(distancia, 3) + 9.11240658041379E-07 * potencia(distancia, 2) + 0.0153686375230109 * distancia - 0.0944227267682436;
                } else {
                    return -1.597277610991E-18 * potencia(distancia, 6) + 3.75300836843857E-14 * potencia(distancia, 5) - 3.69109543097062E-10 * potencia(distancia, 4)
                            + 0.0000019437171945002 * potencia(distancia, 3) - 0.00577371455465558 * potencia(distancia, 2) + 9.1735979616718 * distancia - 6063.27216765854;
                }
            case 4:
                if (isRasante()) {
                    return -2.112255833E-20 * potencia(distancia, 6) + 4.03967131900379E-16 * potencia(distancia, 5) - 2.93815705026912E-12 * potencia(distancia, 4)
                            + 9.97445030290551E-09 * potencia(distancia, 3) - 0.000016502718341991 * potencia(distancia, 2) + 0.0248198910582395 * distancia - 2.66835184245372;
                } else {
                    return 2.35600055077E-19 * potencia(distancia, 6) - 8.38344600171586E-15 * potencia(distancia, 5) + 1.19518166342681E-10 * potencia(distancia, 4)
                            - 8.82428304538655E-07 * potencia(distancia, 3) + 0.00357968327757703 * potencia(distancia, 2) - 7.58218660673342 * distancia + 6584.36426012293;
                }
            case 5:
                if (isRasante()) {
                    return -1.267164801E-21 * potencia(distancia, 6) + 2.6810716913365E-17 * potencia(distancia, 5) - 2.2000344924123E-13 * potencia(distancia, 4)
                            + 9.08569510452888E-10 * potencia(distancia, 3) - 2.57020339305969E-06 * potencia(distancia, 2) + 0.0115801389154626 * distancia - 0.119231925673375;
                } else {
                    return -1.92267064634E-19 * potencia(distancia, 6) + 6.80837978742121E-15 * potencia(distancia, 5) - 9.91030969924869E-11 * potencia(distancia, 4)
                            + 7.57768227534827E-07 * potencia(distancia, 3) - 0.00320429272637321 * potencia(distancia, 2) + 7.09625222805293 * distancia - 6403.6312811945;
                }
            case 6:
                if (isRasante()) {
                    return -4.90319204E-22 * potencia(distancia, 6) + 1.6025712041844E-17 * potencia(distancia, 5) - 2.05112791251571E-13 * potencia(distancia, 4)
                            + 1.3376206443083E-09 * potencia(distancia, 3) - 5.29891786657175E-06 * potencia(distancia, 2) + 0.0123534998160544 * distancia - 0.205025298742839;
                } else {
                    return 1.22755142621E-19 * potencia(distancia, 6) - 5.61116501129275E-15 * potencia(distancia, 5) + 1.06412433425954E-10 * potencia(distancia, 4)
                            - 1.07148767624765E-06 * potencia(distancia, 3) + 0.00604004601933351 * potencia(distancia, 2) - 18.0653127892337 * distancia + 22377.0667299826;
                }
            case 7:
                if (isRasante()) {
                    return 2.69876035E-22 * potencia(distancia, 6) - 8.090847611085E-18 * potencia(distancia, 5) + 7.50814990968402E-14 * potencia(distancia, 4)
                            - 9.83686989961513E-11 * potencia(distancia, 3) - 2.34229815373244E-06 * potencia(distancia, 2) + 0.0109509785004192 * distancia - 0.523975759545843;
                } else {
                    return 2.7550052588E-20 * potencia(distancia, 6) - 1.4906985646852E-15 * potencia(distancia, 5) + 3.35273806768238E-11 * potencia(distancia, 4)
                            - 4.01203865289398E-07 * potencia(distancia, 3) + 0.00269362598238276 * potencia(distancia, 2) - 9.61679593113453 * distancia + 14229.507763294;
                }
            default:
                return 0;
        }
    }

    @Override
    public double getGOrdenadaMax_(double elevacion, int carga) {
        switch (carga) {
            case 1:
                return -1.05686870492861E-06 * potencia(elevacion, 3) + 0.00250717757318515 * potencia(elevacion, 2) - 0.117371051520462 * elevacion + 7.92266727617676;
            case 2:
                return -2.84736113867831E-10 * potencia(elevacion, 4) - 5.04638760383513E-07 * potencia(elevacion, 3) + 0.0023271081030939 * potencia(elevacion, 2) + 0.0389170972715547 * elevacion - 2.9681289777169;

            case 3:
                return -3.67119238689518E-10 * potencia(elevacion, 4) - 6.05909461695933E-07 * potencia(elevacion, 3) + 0.00279952887900237 * potencia(elevacion, 2) + 0.0309764892767816 * elevacion - 1.52287437982159;

            case 4:
                return -4.6297080055918E-10 * potencia(elevacion, 4) - 7.69176373066616E-07 * potencia(elevacion, 3) + 0.00352444816260444 * potencia(elevacion, 2) + 0.0564624207972884 * elevacion - 2.55182213534135;

            case 5:
                return -2.34131199852182E-10 * potencia(elevacion, 4) - 1.71484670220823E-06 * potencia(elevacion, 3) + 0.00495042922785416 * potencia(elevacion, 2) - 0.00826171002245246 * elevacion - 0.218552758888109;

            case 6:
                return -5.10483415580888E-10 * potencia(elevacion, 4) - 1.49313678534865E-06 * potencia(elevacion, 3) + 0.00557754074390004 * potencia(elevacion, 2) + 0.136022762259017 * elevacion - 2.70585933781695;

            case 7:
                return -6.01511682097548E-10 * potencia(elevacion, 4) - 1.70753494975473E-06 * potencia(elevacion, 3) + 0.00649510790346497 * potencia(elevacion, 2) + 0.522935559513826 * elevacion - 11.3073487002985;

            default:
                return 0;
        }
    }

    @Override
    public double getTG_ErrorProb_Alcance(double tiempoV, int carga) {
        switch (carga) {
            case 1:
                return 5.86150525554163E-07*potencia(tiempoV,6 )- 0.0000685598939500816*potencia(tiempoV,5 )+ 0.00305647054588065*potencia(tiempoV,4 )- 0.0672959480357005*potencia(tiempoV,3 )+ 0.82009260395265*potencia(tiempoV,2 )- 4.32344340278413*potencia(tiempoV,1 )+ 10.618549173665;
            case 2:
                return 2.48630294859276E-07*potencia(tiempoV,6 )- 0.000024155266668071*potencia(tiempoV,5 )+ 0.000877698997411169*potencia(tiempoV,4 )- 0.0126370389471905*potencia(tiempoV,3 )+ 0.00261413964095948*potencia(tiempoV,2 )+ 1.66284752236794*potencia(tiempoV, 1)- 1.63969198152344;

            case 3:
                return 1.63182343641127E-07*potencia(tiempoV,6 )- 0.0000230578010115891*potencia(tiempoV,5 )+ 0.00130244849938773*potencia(tiempoV,4 )- 0.0379397473985861*potencia(tiempoV,3 )+ 0.594933612422334*potencia(tiempoV,2 )- 3.7351519662083*potencia(tiempoV, 1)+ 12.1589304253127;

            case 4:
                return -2.42859319651151E-08*potencia(tiempoV,6 )+ 4.51901336287299E-06*potencia(tiempoV,5 )- 0.000310546049158757*potencia(tiempoV,4 )+ 0.0101594900514098*potencia(tiempoV,3 )- 0.170377221998022*potencia(tiempoV,2 )+ 2.150917519563*potencia(tiempoV, 1)+ 0.142389512104418;

            case 5:
                return 2.92597143649459E-09*potencia(tiempoV,6 )+ 1.59375518806767E-07*potencia(tiempoV,5 )- 0.0000494318358288356*potencia(tiempoV,4 )+ 0.00248473785688268*potencia(tiempoV,3 )- 0.0562388655118524*potencia(tiempoV,2 )+ 1.50846590849699*potencia(tiempoV, 1)- 0.334387942622229;
            case 6:
                return 1.31892560587292E-08*potencia(tiempoV,6 )- 2.48595800013725E-06*potencia(tiempoV,5 )+ 0.000176122227001085*potencia(tiempoV,4 )- 0.00554205361046939*potencia(tiempoV,3 )+ 0.0577915239759274*potencia(tiempoV,2 )+ 0.801905087018687*potencia(tiempoV,1 )+ 1.49765372399795;
            case 7:
                return 1.20917941187192E-09*potencia(tiempoV,6 )- 8.20520310502157E-08*potencia(tiempoV,5 )- 4.30694123834373E-06*potencia(tiempoV,4 )+ 0.000689493204284556*potencia(tiempoV,3 )- 0.0372158079141543*potencia(tiempoV,2 )+ 1.09903235660525*potencia(tiempoV,1 )+ 0.584334686477133;
            default:
                return 0;
        }
    }

    @Override
    public double getTG_ErrorProb_Azimut(double tiempoV, int carga) {
        switch (carga) {
            case 1:
                return 1.60841027180975E-07*potencia(tiempoV,6 )- 0.0000186458342128476*potencia(tiempoV,5 )+ 0.00081013962245903*potencia(tiempoV,4 )- 0.016094531395943*potencia(tiempoV,3 )+ 0.155631180147592*potencia(tiempoV,2 )- 0.50947901366724*potencia(tiempoV,1 )+ 0.395804131152837;
            case 2:
                return 5.07217100740448E-07*potencia(tiempoV,6 )- 0.0000523291504126772*potencia(tiempoV,5 )+ 0.00200266366921464*potencia(tiempoV,4 )- 0.0343627688214521*potencia(tiempoV,3 )+ 0.241554707423844*potencia(tiempoV,2 )- 0.0997253539671682*potencia(tiempoV,1 )- 1.19292670931882;
            case 3:
                return -2.16229025700246E-08*potencia(tiempoV,6 )+ 0.0000064035263318604*potencia(tiempoV,5 )- 0.000542819111079497*potencia(tiempoV,4 )+ 0.0208164933563822*potencia(tiempoV,3 )- 0.408790121499009*potencia(tiempoV,2 )+ 4.11744643935084*potencia(tiempoV,1 )- 14.7770116446282;
            case 4:
                return 1.99583657534835E-08*potencia(tiempoV,6 )- 1.70729479386821E-06*potencia(tiempoV,5 )+ 0.0000362322020264291*potencia(tiempoV,4 )+ 0.000800273036204072*potencia(tiempoV,3 )- 0.0421177842864324*potencia(tiempoV,2 )+ 0.803101086733808*potencia(tiempoV,1 )- 2.25956242399653;
            case 5:
                return 2.63679856722914E-08*potencia(tiempoV,6 )- 4.26060504549969E-06*potencia(tiempoV,5 )+ 0.000266460011154893*potencia(tiempoV,4 )- 0.00814462174418653*potencia(tiempoV,3 )+ 0.119724114050394*potencia(tiempoV,2 )- 0.354005626596005*potencia(tiempoV,1 )+ 0.270016994899381;
            case 6:
                return 5.28802769628189E-09*potencia(tiempoV,6 )- 8.80870620921678E-07*potencia(tiempoV,5 )+ 0.0000577574731840833*potencia(tiempoV,4 )- 0.00196878165701698*potencia(tiempoV,3 )+ 0.0356955367443713*potencia(tiempoV,2 )+ 0.0210254402239782*potencia(tiempoV,1 )- 0.363568845911547;
            case 7:
                return 1.20917941187192E-09*potencia(tiempoV,6 )- 8.20520310502157E-08*potencia(tiempoV,5 )- 4.30694123834373E-06*potencia(tiempoV,4 )+ 0.000689493204284556*potencia(tiempoV,3 )- 0.0372158079141543*potencia(tiempoV,2 )+ 1.09903235660525*potencia(tiempoV,1 )+ 0.584334686477133;
            default:
                return 0;
        }
    }

    @Override
    public double getH_DistanciaPorRotacionTierra(double distancia, double azimut, double latitud, int carga) {
        return 0;
    }

    public static void main(String[] args) {

        AmmunitionM1 nuevo = new AmmunitionM1();

    }
}

