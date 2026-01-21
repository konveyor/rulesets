import React from 'react';

import { Button } from '@patternfly/react-core/v5';
import { Table, TableHeader, TableBody } from '@patternfly/react-table/v5';
import { CogIcon } from '@patternfly/react-icons/v5';

const App: React.FC = () => {
  return (
    <div>
      <h1>PatternFly Migration Test</h1>
      
      <Button variant="primary">Click Me</Button>
      
      <Table aria-label="Simple table">
        <TableHeader>
          <tr>
            <th>Header 1</th>
            <th>Header 2</th>
          </tr>
        </TableHeader>
        <TableBody>
          <tr>
            <td>Data 1</td>
            <td>Data 2</td>
          </tr>
        </TableBody>
      </Table>
      
      <CogIcon />
    </div>
  );
};

export default App;