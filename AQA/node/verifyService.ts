import {ApiClient} from "./client";
import {VerifyRequest, VerifyResponse} from "./verify";

export class VerifyService extends ApiClient {

    async verify(req: VerifyRequest) {
        return (
            await this.send<VerifyResponse>({
                method: 'POST',
                path: '/v1/auth/otp/verify',
                body: req,
                type: 'json'
            })
        );
    }
}
