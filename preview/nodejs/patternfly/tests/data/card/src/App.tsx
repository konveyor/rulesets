import React from 'react';
import { Card, CardHeader } from '@patternfly/react-core';

const App: React.FC = () => {
  return (
    <div>
      <h1>PatternFly Migration Test</h1>
      
      {/* patternfly-v5-to-patternfly-v6-card-00000 */}
      <Card isSelectableRaised />
      
      {/* patternfly-v5-to-patternfly-v6-card-00010 */}
      <CardHeader selectableActions={{ name: 'test', selectableActionId: 'id' }}>Content</CardHeader>
    </div>
  );
};

export default App;