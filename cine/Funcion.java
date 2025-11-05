// Funcion.java
public class Funcion {
    private Pelicula pelicula;
        private Sala sala;
            private String horario; // simple por ahora
                private double precio;

                    public Funcion(Pelicula pelicula, Sala sala, String horario, double precio) {
                            this.pelicula = pelicula;
                                    this.sala = sala;
                                            this.horario = horario;
                                                    this.precio = precio;
                                                        }

                                                            public Pelicula getPelicula() { return pelicula; }
                                                                public Sala getSala() { return sala; }
                                                                    public String getHorario() { return horario; }
                                                                        public double getPrecio() { return precio; }

                                                                            @Override
                                                                                public String toString() {
                                                                                        return pelicula.getNombre() + " | Sala " + sala.getIdSala() + " | " + horario + " | $"+precio;
                                                                                            }
                                                                                            }