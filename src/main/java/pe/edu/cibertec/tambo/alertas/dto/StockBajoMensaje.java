package pe.edu.cibertec.tambo.alertas.dto;

public record StockBajoMensaje(Long productoId, Integer stockActual, String nombre) {

    public static StockBajoMensaje desdeTexto(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("mensaje vacío");
        }
        String[] partes = texto.trim().split("\\|", 3);
        if (partes.length != 3 || partes[2].isBlank()) {
            throw new IllegalArgumentException("se esperaba productoId|stockActual|nombre");
        }
        Long productoId = Long.parseLong(partes[0].trim());
        Integer stockActual = Integer.parseInt(partes[1].trim());
        return new StockBajoMensaje(productoId, stockActual, partes[2].trim());
    }
}
