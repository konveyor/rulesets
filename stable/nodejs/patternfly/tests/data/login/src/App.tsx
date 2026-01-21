import React from 'react';
import { LoginMainFooterLinksItem, LoginPage } from '@patternfly/react-core';

const App: React.FC = () => {
  return (
    <LoginPage>
      <div>
        {/* patternfly-v5-to-patternfly-v6-login-00000 */}
        <LoginMainFooterLinksItem href="https://github.com/login">Content</LoginMainFooterLinksItem>
      </div>
    </LoginPage>
  );
};

export default App;