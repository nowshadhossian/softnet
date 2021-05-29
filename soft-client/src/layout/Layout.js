import React from "react";

const Layout = ({title, children, classname}) => {
    return (
        <React.Fragment>
            <div className="container">
                <div className={classname}>
                <h1>{title}</h1>
                {children}
                </div>
            </div>
        </React.Fragment>
    );
};

export default Layout;