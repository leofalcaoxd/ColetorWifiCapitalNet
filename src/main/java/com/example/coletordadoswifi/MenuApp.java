package com.example.coletordadoswifi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MenuApp {
    // Instância do gerenciador de clientes
    private static final ClienteManager clienteManager = new ClienteManager();

    public static void main(String[] args) {
        // Inicializa o banco de dados
        DatabaseManager.initializeDatabase();

        // Cria e configura o frame principal da aplicação
        JFrame frame = new JFrame("Coletor de Wifi - CapitalNet - By Leonardo Falcão");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar o frame
        frame.setSize(640, 360); // Define o tamanho do frame
        frame.setLayout(new BorderLayout()); // Define o layout principal

        // Cria o painel que conterá os botões do menu
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridBagLayout()); // Usa GridBagLayout para o painel dos botões
        GridBagConstraints gbc = new GridBagConstraints(); // Configura as restrições de layout
        gbc.insets = new Insets(10, 10, 10, 10); // Define o espaçamento ao redor dos botões

        // Criação dos botões do menu
        JButton btnAdicionar = new JButton("Adicionar Cliente");
        JButton btnConsultar = new JButton("Consultar Cliente");
        JButton btnRemover = new JButton("Remover Cliente");
        JButton btnSair = new JButton("Sair");

        // Ajusta o tamanho da fonte dos botões
        Font buttonFont = new Font("Arial", Font.PLAIN, 20);
        btnAdicionar.setFont(buttonFont);
        btnConsultar.setFont(buttonFont);
        btnRemover.setFont(buttonFont);
        btnSair.setFont(buttonFont);

        // Adiciona botões ao painel com padding
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Faz com que cada botão ocupe toda a largura disponível
        gbc.gridy = 1; // Define a posição vertical para o primeiro botão
        btnPanel.add(btnAdicionar, gbc); // Adiciona o botão "Adicionar Cliente"

        gbc.gridy = 2; // Atualiza a posição vertical para o próximo botão
        btnPanel.add(btnConsultar, gbc); // Adiciona o botão "Consultar Cliente"

        gbc.gridy = 3; // Atualiza a posição vertical para o próximo botão
        btnPanel.add(btnRemover, gbc); // Adiciona o botão "Remover Cliente"

        gbc.gridy = 4; // Atualiza a posição vertical para o próximo botão
        btnPanel.add(btnSair, gbc); // Adiciona o botão "Sair"

        // Adiciona o painel com botões ao frame principal
        frame.add(btnPanel, BorderLayout.CENTER);

        // Define ações para cada botão
        btnAdicionar.addActionListener(e -> handleAdicionarCliente()); // Ação para adicionar cliente
        btnConsultar.addActionListener(e -> handleConsultarCliente()); // Ação para consultar cliente
        btnRemover.addActionListener(e -> handleRemoverCliente()); // Ação para remover cliente
        btnSair.addActionListener(e -> System.exit(0)); // Ação para sair da aplicação

        // Cria e configura o painel para exibir a data e hora
        JPanel bottomPanel = new JPanel();
        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        bottomPanel.add(timeLabel); // Adiciona o rótulo de tempo ao painel
        frame.add(bottomPanel, BorderLayout.SOUTH); // Adiciona o painel ao frame

        // Atualiza a data e hora a cada segundo
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    timeLabel.setText(sdf.format(new Date())); // Atualiza o rótulo com a data e hora atual
                });
            }
        }, 0, 1000); // Inicializa imediatamente e atualiza a cada 1000 milissegundos (1 segundo)

        // Centraliza o frame na tela
        frame.setLocationRelativeTo(null);

        // Exibe o frame na tela
        frame.setVisible(true);
    }

    // Método para tratar a adição de um novo cliente
    private static void handleAdicionarCliente() {
        // Criação dos campos de entrada para o cliente
        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField nomeWifiField = new JTextField();
        JTextField senhaWifiField = new JTextField();
        JTextField enderecoField = new JTextField();

        // Criação do painel para organizar os campos de entrada
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // GridLayout com 6 linhas e 2 colunas
        panel.add(new JLabel("Nome:")); // Rótulo para o campo de nome
        panel.add(nomeField); // Campo de entrada para o nome
        panel.add(new JLabel("CPF:")); // Rótulo para o campo de CPF
        panel.add(cpfField); // Campo de entrada para o CPF
        panel.add(new JLabel("Telefone:")); // Rótulo para o campo de telefone
        panel.add(telefoneField); // Campo de entrada para o telefone
        panel.add(new JLabel("Nome do Wifi:")); // Rótulo para o campo de nome do Wifi
        panel.add(nomeWifiField); // Campo de entrada para o nome do Wifi
        panel.add(new JLabel("Senha do Wifi:")); // Rótulo para o campo de senha do Wifi
        panel.add(senhaWifiField); // Campo de entrada para a senha do Wifi
        panel.add(new JLabel("Endereço:")); // Rótulo para o campo de endereço
        panel.add(enderecoField); // Campo de entrada para o endereço

        // Exibe a caixa de diálogo para entrada de dados
        int result = JOptionPane.showConfirmDialog(null, panel, "Adicionar Cliente", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            String cpf = cpfField.getText();
            String telefone = telefoneField.getText();
            String nomeWifi = nomeWifiField.getText();
            String senhaWifi = senhaWifiField.getText();
            String endereco = enderecoField.getText();

            if (nome.isEmpty() || cpf.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome e CPF são obrigatórios.");
                return;
            }

            Cliente cliente = new Cliente(nome, cpf, telefone, nomeWifi, senhaWifi, endereco);
            clienteManager.addCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
        }
    }

    // Método para tratar a consulta de um cliente
    private static void handleConsultarCliente() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");

        if (cpf != null && !cpf.trim().isEmpty()) {
            Cliente cliente = clienteManager.getCliente(cpf);

            if (cliente != null) {
                JOptionPane.showMessageDialog(null, cliente.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            }
        }
    }

    // Método para tratar a remoção de um cliente
    private static void handleRemoverCliente() {
        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente a ser removido:");

        if (cpf != null && !cpf.trim().isEmpty()) {
            boolean isRemoved = clienteManager.removeCliente(cpf);

            if (isRemoved) {
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado ou não pôde ser removido.");
            }
        }
    }
}
