package guru.qa.utils;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static guru.qa.config.Prop.PROP;
import static guru.qa.utils.DeviceHelper.executeSh;

public class ApkInfoHelper {

    /**
     * AndroidManifest.xml файл в виде строки из apk файла
     */
    private String apkInfo;

    /**
     * Конструктор в котором происходит чтение apk файла из ресурсов и инициализируется переменная apkInfo
     */
    public ApkInfoHelper() {
        String app = PROP.getLocalAndroidAppPath(); //читаем путь к apk из пропертей
        if(app == null || app.isEmpty()){ //если путь к apk файлу не указан, выкидываем ошибку
            throw new RuntimeException("No value for key 'app' providing apk path in properties");
        }
        try {
            String pathToAapt = "C:\\Users\\Danilec\\AppData\\Local\\Android\\Sdk\\build-tools\\30.0.3\\";
            //вызываем bash команду aapt dumb banding путь к apk, чтобы прочитать AndroidManifest.xml из apk файла
            apkInfo = executeSh(pathToAapt + "aapt dumb badging " + PROP.getLocalAndroidAppPath());
        } catch (IOException | InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * Получаем AppPackage из apk с помощью регулярки
     * @return
     */
    public String getAppPackageFromApk() {
        return findGroup1ValueFromString(apkInfo,"package: name='\\s*([^']+?)\\s*'");
    }

    /**
     * Получаем MainActivity из apk с помощью регулярки
     * @return
     */
    public String getAppMainActivity()  {
        return findGroup1ValueFromString(apkInfo, "launchable-activity: name='\\s*([^']+?)\\s*'");
    }

    /**
     * Находит результат первой группы с помощью регулярок
     * @param text текст в котором нужно искать
     * @param regex регулярное выражение
     * @return результат 1 группы
     */
    private static String findGroup1ValueFromString(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()){
            return matcher.group(1);
        }
        return null;
    }
}
