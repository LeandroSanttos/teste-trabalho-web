package com.cursojava.curso.entities.enums;

public enum PedidoStatus {
    AGUARDANDO_PAGAMENTO(1),
    PAGO(2),
    ENVIADO(3),
    ENTREGUE(4),
    CANCELADO(5);

    private int code;

    private PedidoStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PedidoStatus valueOf(int code) {
        for (PedidoStatus value : PedidoStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("O código do status do pedido é inválido!");
    }
}
