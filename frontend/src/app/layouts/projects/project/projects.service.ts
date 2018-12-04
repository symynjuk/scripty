import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ProjectsService {
    getProjects() {
        return this.httpClient.get('http://www.mocky.io/v2/5c069ae53300006300ef2b26');
    }

    getMoreProjects() {
        return this.httpClient.get('http://www.mocky.io/v2/5c069cbb3300006c00ef2b30');
    }

    constructor(private httpClient: HttpClient) {
    }
}
