import { GlobalExceptionHandler } from "./global-exception-handler";

describe('GlobalExceptionHandler', () => {

    it('logs exception to the error console', () => {
        spyOn(console, 'error');
        const error = new Error('Some error');

        GlobalExceptionHandler.handleException(error);

        expect(console.error).toHaveBeenCalledOnceWith('An unexpected error occurred', error);
    });
});