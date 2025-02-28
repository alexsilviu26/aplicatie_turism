
                     Aplicatie turistica
    
    Introducere
        Aceasta tema isi propune realizarea unei aplicatii turistice care sa
    faciliteze planificarea si organizarea grupurilor si evenimentelor din 
    cadrul muzeelor. Aplicatia permite adaugarea de muzee, a grupurilor de
    turisti, a evenimentelor, dar si a gestionarea acestora.

    Structura Proiectului
        Acest poriect este organizat in mai multe clase, fiecare modeland o
    componenta specifica a procesului de organizare a evenimentelor si grupurilor
    de turisti. Fiecare clasa este implementata in fisiere separate pentru a asigura
    modularitatea si claritatea codului. Atfel avem 3 tipuri de fisiere:
    -> Clasele de tip exceptie
    -> Clasa de tip Enum
    -> Clasele standard Java

    Clasa de tip enumerare:
        Aceasta contine tipurile de cale de prelucrare a comenzilor si 
    metode de tip constructor si getter pentru a obtine informatiile necesare.

    Clasele de tip exceptie:
        Acestea contin exceptiile specifice temei, precum exceptia pentru
    pentru grupul inexistent, pentru evenimentul inexistent, pentru neconformitatea
    tipului ghidului, dar si alte exceptii posibile in cadrul temei. In ceea
    ce priveste aceste exceptii acestea sunt gestionate fie in cadrul metodelor
    in care pot aparea, fie in cadrul metodei main. De asemenea am creat niste exceptii
    si mai specifice pentru a putea gestiona cu usurinta anumite situatii si pentru
    a facilita afisarea mesajelor de eroare.

    Clasele standard Java:
        Printre acestea regasim: 
            ->Database: clasa ce modeleaza baza de date a aplicatiei, aceasta
                        urmareste design pattern-ul Singleton pentru a exista in mod 
                        unic si contine metode de obtinere a grupurilor si membrilor
                        dupa anumite criterii.
            ->Group: clasa ce modeleaza grupurile de turisti, aceasta contine 
                     informatii despre grup, precum numele, ghidul, dar si lista
                     de membri ai grupului. Aceasta clasa contine metode pentru
                     adaugarea si stergerea de membri, dar si pentru obtinerea
                     informatiilor despre grup. In cadrul acestei clase am folosit
                    design pattern-ul Builder pentru a construi obiecte de tip Group,
                    cu toate ca acesta clasa nu are atribute de tip optional, am
                    considerat ca ar fi mai usor de folosit in cadrul temei.
            -> Location: clasa modeleaza locatiile la care se afla muzeele. In cadrul acesteia 
                        regasim informatii atat optionale cat si necesare precum 
                        regiunea si codul SIRUTA. Asadar, avand in vedere atributele
                        am considerat design pattern-ul Builder ca fiind cel mai potrivit
                        pentru aceasta clasa si pentru setarea atributelor.
            -> Museum: clasa ce modeleaza muzeele, aceasta contine informatii despre
                        muzeu, precum numele, locatia. In cadrul acestei clase am folosit
                        design pattern-ul Builder pentru a construi obiecte de tip Museum
                        deoarece existau cazuri in care nu aveam toate informatiile despre
                        muzeu. De asemenea in aceasta clasa isi fac simtite prezanta caracterisiticile
                        design patternului Observer, aceasta clasa fiind subiectul ce 
                        creeaza evenimente si notifica ghizii referitor la acestea.
                        Asadar ghizii sunt observatori ai muzeelor si sunt notificati
                        in cazul in care se schimba ceva la muzeu.
            -> Person: clasa ce modeleaza persoanele, aceasta contine informatii despre
                        persoana, precum numele, varsta, dar si rolul acesteia.
                        In cadrul acestei clase am folosit design pattern-ul Builder pentru
                        a construi obiecte de tip Person, deoarece existau cazuri in care
                        nu aveam toate informatiile despre persoana. Un alt design pattern
                        folosit pentru aceasta clasa este Observer, aceasta clasa fiind
                        observatorul ce primeste notificari despre evenimente de la muzeu.
                        Pentru instantierea obiectelor de tip Person am folosit si design pattern-ul
                        Factory, deoarece am necesitat constructia in functie de parametrii
                        primiti a doua tipuri de obiecte de tip Person, Profesor si Student, 
                        iar Factory-ul a fost cel mai potrivit pentru aceasta situatie.
            ->Factory: clasa ce implementeaza metoda de constuctie a obiectelor de tip Person
            -> Student & Profesor: clasa ce extinde clasa Person, continand informatii specifice si
                                   metode pentru gestionarea acestora.
            ->Utils: clasa ce implementeaza functii diverse ce usureaza programul, 
                    fiind folosite in diverse situatii din cadrul aplicatiei.
            ->Main: clasa ce gestioneaza intreaga aplicatie. Aceasta prelucreaza lucrul cu fisierele
                    de input si output, dar si citirea si interpretarea comenzilor in functie de tipul
                    caii si apelarea metodelor din clasa Command specificate in cerinta, fiecare la
                    momentul necesar.
            ->MuseumCommands: include metodele specifice muzeelor din cerinta, adica addMuseum, ce adaauga 
                                un muzeu in baza de date.
            ->GroupCommands: include metode specifice grupurilor, precum adaugarea sau eliminarea 
                            ghizilor sau membrilor din grup, dar si gasirea acestora. 
            ->EventCommands: include metoda specifica evenimentelor, adica addEvent, ce notifica ghizii
                            despre un eveniment la un muzeu.

            Stil și Convenții
        Codul este scris folosind convenția camelCase, cu variabile private, dar si 
    protejate și metode publice.
        Limbajul folosit este limba engleza pentru a urma numele claselor definite in 
    cerinta temei.
        Încapsularea este respectată prin utilizarea variabilelor private si protejate,
    accesibile doar prin metode publice.
        Menzionez ca in tema se vor regasi zone in care codul se repeta, dar aceasta 
    repetare este necesara pentru a putea afisa corect informatiile despre studenti 
    sau despre profesori. Daca nu as fi utilizat aceasta repetitie, afisarea corespunzatoare
    ar fi fost mult mai dificila.

         Design Patterns
         In cadrul temei am folosit mai multe design patterns pentru a putea realiza
    o aplicatie cat mai eficienta si cat mai usor de folosit. Astfel am folosit:
                -> Builder: pentru a construi obiecte de tip Museum, Group, Person si Location
                            in cazuri in care era optionala adaugarea unor atribute, acest
                            design pattern fiind cunscut pentru indeplinirea acestei cerinte.
                -> Observer: pentru a notifica ghizii despre evenimentele de la muzeu, astfel
                            ghizii fiind observatori ai muzeelor si fiind notificati in cazul
                            in care se schimba ceva la muzeu. Acest design pattern a fost ales
                            deoarece este cel mai potrivit pentru a modifica starea(notifica in
                            acest caz observatorilor unui obiect.
                -> Factory: pentru a construi obiecte de tip Person in functie de parametrii primiti
                            Asadar, in functie de parametrii primiti, se va construi un obiect de tip
                            Student sau Profesor. Acest design pattern este considerat cel mai potrivit
                            pentru instantierea obiectelor ce extind o clasa de baza, in functie de 
                            un parametru dat, caz regasit si in cadrul temei.
                -> Singleton: pentru a crea o singura instanta a clasei Database. In tema am avut nevoie
                                de a ma asigura ca exista o singura instanta a clasei Database. Singleton-ul
                                este design pattern-ul ce asigura crearea unei singure clase 
                                si accesul la aceasta in cadrul intregii aplicatii, fix cazul pe care
                                l-am intalnit in cadrul temei referitor la baza de date.
    
    Concluzie
        In concluzie, acest proiect ofera o implementare a unei aplicatii turistice
    care sa faciliteze planificarea si organizarea grupurilor si evenimentelor din
    cadrul muzeelor. Aplicatia permite adaugarea de muzee, a grupurilor de turisti,
    a evenimentelor, dar si a gestionarea acestora. Soluția respectă cerințele și 
    oferă un cod clar, scalabil și bine documentat.
