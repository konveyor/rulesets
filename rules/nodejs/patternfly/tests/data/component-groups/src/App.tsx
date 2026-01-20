import React from 'react';
import { ContentHeader, ErrorState, InvalidObject, LogSnippet, LogSnippetBorderVariant } from '@patternfly/react-component-groups';

const App: React.FC = () => {
  return (
    <div>
      <h1>PatternFly v5 to v6 Migration Test</h1>
      
      {/* Rule patternfly-v5-to-patternfly-v6-component-groups-00000 */}
      <ContentHeader title="Sample Title" />
      
      {/* Rule patternfly-v5-to-patternfly-v6-component-groups-00010 */}
      {/* This rule is triggered by the import statement at the top */}
      
      {/* Rule patternfly-v5-to-patternfly-v6-component-groups-00020 */}
      <ErrorState errorTitle="Sample error title" />
      
      {/* Rule patternfly-v5-to-patternfly-v6-component-groups-00030 */}
      <InvalidObject invalidObjectTitleText="Sample title" />
      
      {/* Rule patternfly-v5-to-patternfly-v6-component-groups-00040 */}
      <LogSnippet message="Failure - check logs for details" logSnippet="code" leftBorderVariant={LogSnippetBorderVariant.success}
/>
    </div>
  );
};

export default App;