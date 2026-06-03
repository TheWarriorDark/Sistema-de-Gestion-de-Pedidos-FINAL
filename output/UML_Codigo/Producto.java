public abstract class Producto {
    private String nombre;
    private float precio;
    public void Producto();
    public void Producto(String nombre, float precio);
    public String getNombre();
    public void setNombre(String nombre);
    public float getPrecio();
    public void setPrecio(float precio);
    public String toString();
    public float calcularPrecio();
}
