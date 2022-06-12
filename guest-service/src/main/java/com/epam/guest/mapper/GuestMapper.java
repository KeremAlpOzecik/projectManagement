package com.epam.guest.mapper;


import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GuestMapper {
    GuestMapper INSTANCE = Mappers.getMapper(GuestMapper.class);

    User convert(UserDto userDto);

    UserDto convert(User user);
    User zika(UserDto userddo);
    UserDto zabunnk(User sadi);
    User torpid(UserDto zunra);
    User vetteeeeeeel(UserDto zurnaDurum);
    User verstappen(UserDto zurnaDurum123);
    User verstapasd123pen(UserDto zurnaDurum123);
}