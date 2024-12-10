package controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResizeImageController {

    private static final int IMG_WIDTH = 100;
    private static final int IMG_HEIGHT = 100;

    // Método principal do Controller
    public void startImageResize() {
        // Exibe uma caixa de diálogo para o usuário selecionar uma imagem
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha a imagem de login");
        
        int result = fileChooser.showOpenDialog(null);
        
        // Se o usuário selecionar um arquivo
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Redimensiona a imagem escolhida
                resizeAndSaveImage(selectedFile);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao redimensionar a imagem: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método que redimensiona a imagem e salva em um novo arquivo
    private void resizeAndSaveImage(File originalFile) throws IOException {
        BufferedImage originalImage = ImageIO.read(originalFile);

        // Calculando nova altura e largura para manter a proporção
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();
        double aspectRatio = (double) originalWidth / originalHeight;

        // Mantém a proporção, ajustando a altura ou a largura, conforme necessário
        int width = IMG_WIDTH;
        int height = IMG_HEIGHT;
        
        if (aspectRatio > 1) {
            height = (int) (width / aspectRatio);
        } else {
            width = (int) (height * aspectRatio);
        }

        // Cria a imagem redimensionada
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        
        // Configurações de qualidade para o redimensionamento
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Redimensiona a imagem original para o tamanho calculado
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        // Definir o novo caminho e nome do arquivo
        String fileName = originalFile.getName();
        String newFileName = "resized-" + fileName;
        Path targetPath = Paths.get(originalFile.getParent(), newFileName);

        // Salva a imagem redimensionada
        ImageIO.write(resizedImage, "PNG", targetPath.toFile());
        
        // Exibe uma mensagem ao usuário
        JOptionPane.showMessageDialog(null, "Imagem redimensionada com sucesso! Salvo em: " + targetPath.toString(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // Inicia o Controller
        ResizeImageController controller = new ResizeImageController();
        controller.startImageResize();
    }
}
