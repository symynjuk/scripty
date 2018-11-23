import { Pipe, PipeTransform } from '@angular/core';
import {Project} from '../models/Project';
@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {
  transform(items: Project[], searchText: string, onlyMyProjects: boolean): Project[] {
    if (!items) { return []; }
    searchText = searchText.toLowerCase();
    items = items.filter( (it: Project) => {
      return it.name.toLowerCase().includes(searchText);
    });
    if (onlyMyProjects) {
      items = items.filter((it: Project) => {
        return it.author === localStorage.getItem('userName');
      });
    }
    return items;
  }
}
