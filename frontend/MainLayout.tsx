import {AppLayout} from '@hilla/react-components/AppLayout.js';
import {DrawerToggle} from '@hilla/react-components/DrawerToggle.js';
import Placeholder from 'Frontend/components/placeholder/Placeholder.js';
import {getFsViews, useRouteMetadata} from 'Frontend/util/routing.js';
import {Suspense} from 'react';
import {NavLink, Outlet} from 'react-router-dom';
import {useAuth} from "Frontend/auth";
import {Button} from "@hilla/react-components/Button";

const navLinkClasses = ({isActive}: any) => {
    return `block rounded-m p-s ${isActive ? 'bg-primary-10 text-primary' : 'text-body'}`;
};

export default function MainLayout() {
    const currentTitle = useRouteMetadata()?.title ?? 'My App';
    const {logout, state, hasAccess} = useAuth();


    const activeViews =  getFsViews().filter((view) => hasAccess({
        requiresLogin: !!view.rolesAllowed,
        rolesAllowed: view.rolesAllowed
    }));

    return (
        <AppLayout primarySection="drawer">
            <div slot="drawer" className="flex flex-col justify-between h-full p-m">
                <header className="flex flex-col gap-m">
                    <h1 className="text-l m-0">My App</h1>
                    <Button onClick={() => logout()} disabled={!state.user}>Logout</Button>
                    {state.user && <p>Logged in as {state.user.name}</p>}
                    <nav>
                        {
                            getFsViews().map((view) => {
                                return <NavLink key={view.id} className={navLinkClasses}
                                                to={view.route}>{view.title}</NavLink>
                            })
                        }
                    </nav>
                </header>
            </div>

            <DrawerToggle slot="navbar" aria-label="Menu toggle"></DrawerToggle>
            <h2 slot="navbar" className="text-l m-0">
                {currentTitle}
            </h2>

            <Suspense fallback={<Placeholder/>}>
                <Outlet/>
            </Suspense>
        </AppLayout>
    );
}
