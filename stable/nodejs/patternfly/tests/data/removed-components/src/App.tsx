import React, { useState } from 'react';
// Rule patternfly-v5-to-patternfly-v6-removed-components-00000
import { KebabToggle } from '@patternfly/react-core/deprecated';
import { PageNavigation } from '@patternfly/react-core';

const App: React.FC = () => {
  const [isOpen, setIsOpen] = useState(false);

  const onToggle = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="app-container">
      <h1>PatternFly Migration Test App</h1>
      
      <div>
        {/* Rule patternfly-v5-to-patternfly-v6-removed-components-00010 */}
        <KebabToggle>Content</KebabToggle>
      </div>

      <div>
        {/* Rule patternfly-v5-to-patternfly-v6-removed-components-00020 */}
        <PageNavigation>
          <a href="#section1">Section 1</a>
          <a href="#section2">Section 2</a>
        </PageNavigation>
      </div>
    </div>
  );
};

export default App;