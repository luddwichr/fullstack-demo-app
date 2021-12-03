export class GlobalExceptionHandler {
    static handleException(error: Error) {
        console.error('An unexpected error occurred', error);
    }
}