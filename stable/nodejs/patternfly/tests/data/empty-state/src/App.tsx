import React from 'react';
import { EmptyStateHeader, EmptyStateIcon, EmptyState, EmptyStateBody, Button, Title } from '@patternfly/react-core';
import { CubesIcon } from '@patternfly/react-icons';

// Rule patternfly-v5-to-patternfly-v6-empty-state-00000
export { EmptyStateHeader } from '@patternfly/react-core';

// Rule patternfly-v5-to-patternfly-v6-empty-state-00010
export { EmptyStateIcon } from '@patternfly/react-core';

const App: React.FC = () => {
  return (
    <div>
      <h1>PatternFly v5 to v6 Migration Test</h1>
      
      <EmptyState>
        {/* Rule patternfly-v5-to-patternfly-v6-empty-state-00030 */}
        <EmptyStateIcon icon={CubesIcon} />
        
        {/* Rule patternfly-v5-to-patternfly-v6-empty-state-00020 */}
        <EmptyStateHeader titleText="No results found" />
        
        <EmptyStateBody>
          No results match the filter criteria. Clear all filters and try again.
        </EmptyStateBody>
        <Button variant="primary">Clear all filters</Button>
      </EmptyState>
    </div>
  );
};

export default App;