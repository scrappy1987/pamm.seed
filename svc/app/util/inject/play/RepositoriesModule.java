package util.inject.play;

import persistence.infrastrcture.ProjectJpaRepository;
import domain.services.project.ProjectRepository;
import play.Logger;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;

public class RepositoriesModule extends Module
{
    private static final Logger.ALogger logger = Logger.of(RepositoriesModule.class);

    @Override public Seq<Binding<?>> bindings(Environment environment, Configuration configuration)
    {
        logger.info("Binding ProjectRepository to ProjectJpaRepository");

        return seq(bind(ProjectRepository.class).to(ProjectJpaRepository.class));
    }
}
