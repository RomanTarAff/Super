import axios, {Axios, AxiosRequestHeaders, AxiosResponse, Method} from 'axios';

interface RequestOptions {
    method: Method;
    path: string;
    headers?: AxiosRequestHeaders;
    body?: Record<string, unknown>;
    type?: 'form' | 'json';
    isRinkeby?: boolean;
}

export abstract class ApiClient {
    protected readonly client: Axios;

    constructor(url: string) {
        this.client = axios.create({
            baseURL: url,
            headers: {}
        });
    }

    protected async send<T = unknown>(opts: RequestOptions, accessToken?:string): Promise<AxiosResponse<T>> {
        const { method, path } = opts;

        let body;
        let headers;

        // if (opts.type === 'form' && opts.body) {
        //     body = toFormData(opts.body, {
        //         indices: true,
        //         nullsAsUndefineds: true,
        //         booleansAsIntegers: true
        //     });
        //     headers = body.getHeaders();
        // }

        if (opts.type === 'json' && opts.body && !opts.isRinkeby) {
            body = JSON.stringify(opts.body);
            headers = {
                'Content-Type': 'application/json'
            };
        }

        if(accessToken != undefined) {
            headers  = {
                'Cookie': `__sf_at=${accessToken}; Path=/; Secure; HttpOnly;`
            }
            //console.log(`Access token:${accessToken}`);
        }

        try {

            const res = await this.client.request({
                method: method,
                url: path,
                headers: headers,
                data: body
            });
           // console.log(`Trying api request to ${opts.method} ${res.config.baseURL}${opts.path}`, opts.body);
            return res;
        } catch (err) {
            if (axios.isAxiosError(err)) {
                console.log(
                    `Request failed to ${opts.method} ${err.config.baseURL}${opts.path} \n${JSON.stringify(err.response?.data, null, 2)}`,
                    'error'
                );
            }

            // eslint-disable-next-line @typescript-eslint/ban-ts-comment
            // @ts-ignore
            return err.response;
       }
    }
}
