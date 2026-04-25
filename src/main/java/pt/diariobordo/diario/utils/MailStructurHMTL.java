package pt.diariobordo.diario.utils;

public class MailStructurHMTL {

    public static String createNewUserHtmlMail(String username, String userpassword, String usermail){

        return "<!DOCTYPE html>\n" +
                "<html lang=\"pt-BR\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Acesso à Plataforma</title>\n" +
                "    <style>\n" +
                "        body { margin: 0; padding: 0; background-color: #f0f2f5; }\n" +
                "        table { border-spacing: 0; }\n" +
                "        td { padding: 0; }\n" +
                "        img { border: 0; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0; background-color: #f0f2f5; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\">\n" +
                "\n" +
                "    <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"background-color: #f0f2f5;\">\n" +
                "        <tr>\n" +
                "            <td align=\"center\" style=\"padding: 40px 20px;\">\n" +
                "                \n" +
                "                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"background-color: #ffffff; max-width: 400px; border-radius: 8px; border: 1px solid #e0e0e0;\">\n" +
                "                    <tr>\n" +
                "                        <td style=\"padding: 30px;\">\n" +
                "                            \n" +
                "                            <h2 style=\"color: #333333; text-align: center; margin-top: 0; margin-bottom: 25px; border-bottom: 2px solid #f0f2f5; padding-bottom: 15px; font-size: 22px;\">\n" +
                "                                Dados de Acesso ao Diário de Bordo\n" +
                "                            </h2>\n" +
                "                            \n" +
                "                            <p style=\"margin: 0 0 15px 0; font-size: 16px; line-height: 1.5;\">\n" +
                "                                <strong style=\"color: #555555;\">Nome:</strong> \n" +
                "                                <span style=\"color: #222222;\">"+username+"</span>\n" +
                "                            </p>\n" +
                "\n" +
                "                            <p style=\"margin: 0 0 15px 0; font-size: 16px; line-height: 1.5;\">\n" +
                "                                <strong style=\"color: #555555;\">Email:</strong> \n" +
                "                                <span style=\"color: #222222;\">"+ usermail+" </span>\n" +
                "                            </p>\n" +
                "\n" +
                "                            <p style=\"margin: 0 0 30px 0; font-size: 16px; line-height: 1.5;\">\n" +
                "                                <strong style=\"color: #555555;\">Senha:</strong> \n" +
                "                                <span style=\"color: #222222;\">"+userpassword+"</span>\n" +
                "                            </p>\n" +
                "\n" +
                "                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
                "                                <tr>\n" +
                "                                    <td align=\"center\">\n" +
                "                                        <a href=\"https://diario-bordo-frontend.vercel.app\" target=\"_blank\" style=\"background-color: #0066cc; border: 1px solid #0066cc; border-radius: 6px; color: #ffffff; display: inline-block; font-family: sans-serif; font-size: 16px; font-weight: bold; line-height: 48px; text-align: center; text-decoration: none; width: 100%; -webkit-text-size-adjust: none;\">\n" +
                "                                            Acessar Plataforma\n" +
                "                                        </a>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </table>\n" +
                "\n" +
                "                            <p style=\"margin: 20px 0 0 0; font-size: 13px; line-height: 1.5; color: #666666; text-align: center; background-color: #f9f9f9; padding: 10px; border-radius: 6px; border: 1px solid #eeeeee;\">\n" +
                "                                ⚠\uFE0F <strong>Atenção:</strong> Por favor, utilize um <strong>computador</strong> para acessar o link acima. No momento, nossa plataforma não é funcional em celulares ou tablets.\n" +
                "                            </p>\n" +
                "\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "                </td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

    }

}
