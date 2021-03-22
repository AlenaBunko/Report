package my.project.report;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

    @Override
<<<<<<< HEAD
    protected SpringApplicationBuilder configure (SpringApplicationBuilder application){
        return  application.sources(ReportApplication.class);
    }
}
=======
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReportApplication.class);
    }

}
>>>>>>> 3614060371443a13d3266ffd672b52bde2162ce4
