import {configureAuth} from '@hilla/react-auth';
import {UserInfoService} from 'Frontend/generated/endpoints';

// Configure auth to use `UserInfoService.getUserInfo`
const auth = configureAuth(UserInfoService.getUserInfo, {
    getRoles: (user) => user.authorities
        ? user.authorities.filter(a => a !== undefined) as string[]
        : [],
});

// Export auth provider and useAuth hook, which are automatically
// typed to the result of `UserInfoService.getUserInfo`
export const useAuth = auth.useAuth;
export const AuthProvider = auth.AuthProvider;