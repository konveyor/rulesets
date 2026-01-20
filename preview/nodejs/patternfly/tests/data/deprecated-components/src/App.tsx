import React from 'react';
import { Chip, DragDrop, Draggable, Droppable, DualListSelector, Modal, Tile } from '@patternfly/react-core';

const App: React.FC = () => {
  const [isModalOpen, setIsModalOpen] = React.useState(false);
  const [isAboutModalOpen, setIsAboutModalOpen] = React.useState(false);

  return (
    <div>
      <h1>PatternFly Migration Test</h1>
      
      {/* Rule patternfly-v5-to-patternfly-v6-deprecated-components-00000 */}
      <AboutModal
        isOpen={isAboutModalOpen}
        onClose={() => setIsAboutModalOpen(false)}
        productName="Product Name"
        brandImageSrc="logo.png"
        brandImageAlt="Logo"
      />

      {/* Rule patternfly-v5-to-patternfly-v6-deprecated-components-00010 */}
      <ApplicationLauncher
        items={[{ name: 'Application 1', id: 'app1' }]}
        onSelect={() => {}}
        isOpen={false}
      />

      {/* Rule patternfly-v5-to-patternfly-v6-deprecated-components-00020 */}
      <CatalogTile
        id="catalog-tile"
        title="Catalog Tile"
        description="This is a catalog tile"
      />

      {/* Rule patternfly-v5-to-patternfly-v6-deprecated-components-00030 */}
      <ChipGroup categoryName="Category">
        <div>Chip content</div>
      </ChipGroup>

      {/* Rule patternfly-v5-to-patternfly-v6-deprecated-components-00040 */}
      <ContextSelector
        toggleText="Select"
        onSelect={() => {}}
        isOpen={false}
        items={[{ name: 'Context 1', id: 'ctx1' }]}
      />

      {/* Rule patternfly-v5-to-patternfly-v6-deprecated-components-00050 */}
      <Modal
        isOpen={isModalOpen}
        title="Modal Title"
        onClose={() => setIsModalOpen(false)}
      >
        Modal content
      </Modal>

      {/* Rule patternfly-v5-to-patternfly-v6-deprecated-components-00060 */}
      <Tile
        title="Tile Title"
        isSelected={false}
        onClick={() => {}}
      >
        Tile content
      </Tile>
    </div>
  );
};

export default App;