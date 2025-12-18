import React from 'react';
import { Button } from '@patternfly/react-core';
import { Card } from '@patternfly/react-core'; /* data-codemods */

const App: React.FC = () => {
  return (
    <div>
      <h1>PatternFly v5 to v6 Migration Test</h1>

      {/* Rule patternfly-v5-to-patternfly-v6-cleanup-00000 */}
      <Component data-codemods="true" />

      {/* Rule patternfly-v5-to-patternfly-v6-cleanup-00010 */}
      <Button>Click me</Button>
    </div>
  );
};

// Simple component for testing
const Component: React.FC<any> = (props) => {
  return <div {...props}>Test Component</div>;
};

export default App;