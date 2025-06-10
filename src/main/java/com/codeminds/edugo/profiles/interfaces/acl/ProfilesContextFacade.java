package com.codeminds.edugo.profiles.interfaces.acl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeminds.edugo.profiles.domain.model.queries.GetDriverByIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetProfileByUserIdQuery;
import com.codeminds.edugo.profiles.domain.model.queries.GetParentByIdQuery;
import com.codeminds.edugo.iam.domain.model.aggregates.User;
import com.codeminds.edugo.profiles.domain.model.aggregates.Profile;
import com.codeminds.edugo.profiles.domain.model.commands.CreateDriverCommand;
import com.codeminds.edugo.profiles.domain.model.commands.CreateParentCommand;
import com.codeminds.edugo.profiles.domain.model.entities.Driver;
import com.codeminds.edugo.profiles.domain.model.entities.Parent;
import com.codeminds.edugo.profiles.domain.services.DriverCommandService;
import com.codeminds.edugo.profiles.domain.services.DriverQueryService;
import com.codeminds.edugo.profiles.domain.services.ParentCommandService;
import com.codeminds.edugo.profiles.domain.services.ParentQueryService;
import com.codeminds.edugo.profiles.domain.services.ProfileQueryService;

@Service
public class ProfilesContextFacade {
    private final ProfileQueryService profileQueryService;
    private final DriverQueryService driverQueryService;
    private final DriverCommandService driverCommandService;
    private final ParentQueryService parentQueryService;
    private final ParentCommandService parentCommandService;

    public ProfilesContextFacade(
            ProfileQueryService profileQueryService,
            DriverQueryService driverQueryService,
            DriverCommandService driverCommandService,
            ParentQueryService parentQueryService,
            ParentCommandService parentCommandService) {
        this.profileQueryService = profileQueryService;
        this.driverQueryService = driverQueryService;
        this.driverCommandService = driverCommandService;
        this.parentQueryService = parentQueryService;
        this.parentCommandService = parentCommandService;
    }

    public Optional<Profile> fetchProfileByDriverId(Long driverId) {
        var driverProfileQuery = new GetDriverByIdQuery(driverId);
        var driver = driverQueryService.handle(driverProfileQuery);
        if (driver.isEmpty())
            return Optional.empty();
        Long userId = driver.get().getUserId();
        var profileQuery = new GetProfileByUserIdQuery(userId);
        return profileQueryService.handle(profileQuery);
    }

    public Optional<Profile> fetchProfileByParentId(Long parentId) {
        var parentProfileQuery = new GetParentByIdQuery(parentId);
        var parent = parentQueryService.handle(parentProfileQuery);
        if (parent.isEmpty())
            return Optional.empty();
        Long userId = parent.get().getUserId();
        var profileQuery = new GetProfileByUserIdQuery(userId);
        return profileQueryService.handle(profileQuery);
    }

    public Optional<Driver> fetchDriverById(Long driverId) {
        var getDriverByIdQuery = new GetDriverByIdQuery(driverId);
        return driverQueryService.handle(getDriverByIdQuery);
    }

    public Optional<Parent> fetchParentById(Long parentId) {
        var getParentByIdQuery = new GetParentByIdQuery(parentId);
        return parentQueryService.handle(getParentByIdQuery);
    }

    public Long createDriver(Long userId, User user) {
        return driverCommandService.handle(new CreateDriverCommand(userId), user);
    }

    public Long createParent(Long userId, User user) {
        return parentCommandService.handle(new CreateParentCommand(userId), user);
    }

}
