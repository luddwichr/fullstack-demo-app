import { globalExceptionHandler } from "./global-exception-handler";

describe('globalExceptionHandler', () => {

    it('logs exception to the error console', () => {
        spyOn(console, 'error');
        const error = new Error('Some error');

        globalExceptionHandler(error);

        expect(console.error).toHaveBeenCalledOnceWith('An unexpected error occurred', error);
    });
});