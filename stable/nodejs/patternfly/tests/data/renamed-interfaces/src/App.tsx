import React from 'react';
import {
  Toolbar,
  ToolbarContent,
  ToolbarChipGroup,
  ToolbarChip,
  ToolbarChipGroupContentProps
} from '@patternfly/react-core';

const App: React.FC = () => {
  // Sample data
  const items = ['Item 1', 'Item 2', 'Item 3'];

  // Rule patternfly-v5-to-patternfly-v6-renamed-interfaces-00000
  const renderToolbarContent = (props: ToolbarChipGroupContentProps) => {
    return <div>{props.children}</div>;
  };

  return (
    <div>
      <h1>PatternFly Migration Test</h1>
      
      <Toolbar>
        <ToolbarContent>
          {/* Rule patternfly-v5-to-patternfly-v6-renamed-interfaces-00010 */}
          <ToolbarChipGroup categoryName="Category">
            {/* Rule patternfly-v5-to-patternfly-v6-renamed-interfaces-00020 */}
            {items.map((item, index) => (
              <ToolbarChip key={index} onClick={() => console.log(item)}>
                {item}
              </ToolbarChip>
            ))}
          </ToolbarChipGroup>
        </ToolbarContent>
      </Toolbar>

      {/* Using the component with ToolbarChipGroupContentProps */}
      {renderToolbarContent({ children: <div>Content with deprecated props</div> })}
    </div>
  );
};

export default App;