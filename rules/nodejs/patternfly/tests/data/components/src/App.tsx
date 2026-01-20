import React from 'react';
import {
  Button,
  Chip,
  ExpandableSection,
  Text,
  Tile
} from '@patternfly/react-core';

const App: React.FC = () => {
  const isActive = true;

  return (
    <div>
      <h1>PatternFly v5 to v6 Migration Test</h1>

      {/* Rule patternfly-v5-to-patternfly-v6-components-00000 */}
      <Chip>Content</Chip>
      
      {/* Rule patternfly-v5-to-patternfly-v6-components-00010 */}
      <Tile>Example</Tile>
      
      {/* Rule patternfly-v5-to-patternfly-v6-components-00020 */}
      <Text>Example</Text>
      
      {/* Rule patternfly-v5-to-patternfly-v6-components-00030 */}
      <ExpandableSection isActive={isActive}>Content</ExpandableSection>
      
      {/* Rule patternfly-v5-to-patternfly-v6-components-00040 */}
      <Button isActive>Click me</Button>
      
      {/* Rule patternfly-v5-to-patternfly-v6-components-00050 */}
      {/* This rule is covered by the import of Text from '@patternfly/react-core' above */}
      
      {/* Rule patternfly-v5-to-patternfly-v6-components-00060 */}
      {/* This rule is covered by the Button with isActive prop above */}
    </div>
  );
};

export default App;