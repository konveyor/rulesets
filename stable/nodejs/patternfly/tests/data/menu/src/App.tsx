import React, { useState } from 'react';
import {
  Dropdown,
  DropdownToggle,
  DropdownItem,
  KebabToggle,
  DropdownPosition
} from '@patternfly/react-core';

const App: React.FC = () => {
  const [isKebabOpen, setIsKebabOpen] = useState(false);

  const onKebabToggle = (isOpen: boolean) => {
    setIsKebabOpen(isOpen);
  };

  const dropdownItems = [
    <DropdownItem key="action1">Action 1</DropdownItem>,
    <DropdownItem key="action2">Action 2</DropdownItem>,
    <DropdownItem key="action3">Action 3</DropdownItem>
  ];

  return (
    <div className="app-container">
      <h1>PatternFly Migration Test</h1>
      
      {/* Rule patternfly-v5-to-patternfly-v6-menu-00000 */}
      <Dropdown
        onSelect={() => setIsKebabOpen(false)}
        toggle={<KebabToggle onToggle={onKebabToggle} />}
        isOpen={isKebabOpen}
        isPlain
        dropdownItems={dropdownItems}
        position={DropdownPosition.right}
      />
    </div>
  );
};

export default App;