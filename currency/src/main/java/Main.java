
import api.JsonSchemeReaderNbpApi;


public class Main {
    public static void main(String[] args) {
        JsonSchemeReaderNbpApi jsonSchemeReaderNbpApi=new JsonSchemeReaderNbpApi();

        System.out.println(  jsonSchemeReaderNbpApi.loadActualJson().stream()
                .map(s->s.getRates().stream().filter(z->z.getCode().equals("EUR")).findFirst().get()).findFirst().get().getRate());

    }
}
