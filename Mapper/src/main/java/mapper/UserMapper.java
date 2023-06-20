package mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import user.User;
import user.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping (target = "id",source = "id")
    @Mapping (target = "firstName",source = "firstName")
    @Mapping (target = "lastName",source = "lastName")
    @Mapping (target = "email",source = "email")
    UserDto userToUserDto(User user);
}
